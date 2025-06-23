package com.yogiting.api.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogiting.api.common.dto.FileDto;
import com.yogiting.api.common.service.S3Service;
import com.yogiting.api.post.domain.Post;
import com.yogiting.api.post.domain.PostAttachment;
import com.yogiting.api.post.dto.PostCreateReqDto;
import com.yogiting.api.post.dto.PostDetailResDto;
import com.yogiting.api.post.dto.PostEditReqDto;
import com.yogiting.api.post.dto.PostPreviewResDto;
import com.yogiting.api.post.mapper.PostAttachmentMapper;
import com.yogiting.api.post.mapper.PostMapper;
import com.yogiting.api.post.search.document.PostDocument;
import com.yogiting.api.post.search.service.PostSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
public class PostService {

    private final PostMapper postMapper;
    private final PostAttachmentMapper postAttachmentMapper;
    private final S3Service s3Service;
    private final PostLikeService postLikeService;
    private final PostSearchService postSearchService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "posts-cdc";

    public PostService(PostMapper postMapper, PostAttachmentMapper postAttachmentMapper, S3Service s3Service, PostLikeService postLikeService, PostSearchService postSearchService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.postMapper = postMapper;
        this.postAttachmentMapper = postAttachmentMapper;
        this.s3Service = s3Service;
        this.postLikeService = postLikeService;
        this.postSearchService = postSearchService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void createPost(PostCreateReqDto request) {

        Post post = Post.builder()
                .memberId(request.getMemberId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        postMapper.createPost(post);

        List<FileDto> fileDtos = request.getAttachedFiles();

        if (fileDtos != null && !fileDtos.isEmpty()) {
            List<PostAttachment> attachments = fileDtos.stream()
                    .map(dto -> {
                        PostAttachment file = PostAttachment.builder()
                                .postId(post.getId())
                                .originalFilename(dto.getOriginalFilename())
                                .storedFilename(dto.getStoredFilename())
                                .fileUrl(dto.getFileUrl())
                                .fileSize(dto.getFileSize())
                                .contentType(dto.getContentType())
                                .build();
                        return file;
                    })
                    .collect(Collectors.toList());

            postAttachmentMapper.createAttachment(attachments);
        }


        PostDocument postDocument = PostDocument.builder()
                .id(post.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .nickname(request.getMemberNickname())
                .build();

        // Elasticsearch로 저장
        //postSearchService.savePost(postDocument);

        try{
            String postDocumentStr = objectMapper.writeValueAsString(postDocument);
            kafkaTemplate.send(TOPIC, post.getId().toString(), postDocumentStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Kafka 메시지 발송 실패: {}", postDocument, e);
        }
    }

    public PostDetailResDto getPost(Long postId, Long memberId) {
        PostDetailResDto post = postMapper.getPost(postId);
        if (post == null) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }

        Long likeCount = postLikeService.getLikeCount(postId);
        post.setLikeCount(likeCount);

        if (memberId != null) {
            boolean liked = postLikeService.isLikedByMember(postId, memberId);
            post.setLikePost(liked);
        }

        return post;
    }

    public List<PostPreviewResDto> getPostPreview() {
        List<PostPreviewResDto> postPreview = postMapper.getPostPreview(null);
        List<String> likeCounts = postLikeService.getAllLikeCount(postPreview);

        for (int i = 0; i < postPreview.size(); i++) {
            String likeCountStr = likeCounts.get(i);
            Long likeCount = (likeCountStr != null) ? Long.parseLong(likeCountStr) : 0L;
            postPreview.get(i).setLikeCount(likeCount);
        }

        return postPreview;
    }

    public List<PostPreviewResDto> getPostPreviewById(List<Long> postIds) {
        List<PostPreviewResDto> postPreview = postMapper.getPostPreview(postIds);
        List<String> likeCounts = postLikeService.getAllLikeCount(postPreview);

        for (int i = 0; i < postPreview.size(); i++) {
            String likeCountStr = likeCounts.get(i);
            Long likeCount = (likeCountStr != null) ? Long.parseLong(likeCountStr) : 0L;
            postPreview.get(i).setLikeCount(likeCount);
        }

        return postPreview;
    }

    public void editPost(Long postId, PostEditReqDto request) {
        postMapper.editPost(postId, request);

        // 신규 파일 추가
        List<FileDto> newFileDtos = request.getNewAttachedFiles();
        if (newFileDtos != null && !newFileDtos.isEmpty()) {
            List<PostAttachment> attachments = newFileDtos.stream()
                    .map(dto -> {
                        PostAttachment file = PostAttachment.builder()
                                .postId(postId)
                                .originalFilename(dto.getOriginalFilename())
                                .storedFilename(dto.getStoredFilename())
                                .fileUrl(dto.getFileUrl())
                                .fileSize(dto.getFileSize())
                                .contentType(dto.getContentType())
                                .build();
                        return file;
                    })
                    .collect(Collectors.toList());

            postAttachmentMapper.createAttachment(attachments);
        }

        // 삭제된 파일 DB에서 제거
        List<Long> removeFileIds = request.getRemovedAttachedFileIds();
        if (removeFileIds != null && !removeFileIds.isEmpty()) {
            postAttachmentMapper.deleteAttachment(removeFileIds);

            List<String> removeFileNames = request.getRemovedAttachedFileNames();
            String folder = "post";
            s3Service.removeFile(removeFileNames, folder);
        }

        PostDocument postDocument = PostDocument.builder()
                .id(postId)
                .title(request.getTitle())
                .content(request.getContent())
                .nickname(request.getMemberNickname())
                .build();

        // Elasticsearch로 저장
        //postSearchService.savePost(postDocument);

        try{
            String postDocumentStr = objectMapper.writeValueAsString(postDocument);
            kafkaTemplate.send(TOPIC, postId.toString(), postDocumentStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Kafka 메시지 발송 실패: {}", postDocument, e);
        }

    }

    public void deletePost(Long postId) {
        List<String> removeFileNames = postAttachmentMapper.getAttachment(postId);
        System.out.println(removeFileNames.toString());
        String folder = "post";
        s3Service.removeFile(removeFileNames, folder);

        postMapper.deletePost(postId);

        kafkaTemplate.send(TOPIC, postId.toString(), null);
    }
}
