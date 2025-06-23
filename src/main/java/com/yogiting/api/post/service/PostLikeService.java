package com.yogiting.api.post.service;

import com.yogiting.api.post.dto.PostLikeReqDto;
import com.yogiting.api.post.dto.PostPreviewResDto;
import com.yogiting.api.post.mapper.PostLikeMapper;
import com.yogiting.api.post.mapper.PostMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostLikeService {

    private final PostLikeMapper postLikeMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final PostMapper postMapper;

    public PostLikeService(PostLikeMapper postLikeMapper, RedisTemplate<String, String> redisTemplate, PostMapper postMapper) {
        this.postLikeMapper = postLikeMapper;
        this.redisTemplate = redisTemplate;
        this.postMapper = postMapper;
    }

    public List<String> getAllLikeCount(List<PostPreviewResDto> postPreviewResDtos) {
        List<String> postKeys = postPreviewResDtos.stream()
                .map(dto -> "post:likes:" + dto.getId())
                .collect(Collectors.toList());

        return redisTemplate.opsForValue().multiGet(postKeys);
    }

    public void addLikeCount(PostLikeReqDto postLikeReqDto) {

        Long postId = postLikeReqDto.getPostId();
        Long memberId = postLikeReqDto.getMemberId();

        try {
            postLikeMapper.addLikeCount(postId, memberId);
            redisTemplate.opsForValue().increment("post:likes:" + postId);

        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("이미 해당 게시물을 좋아요 표시했습니다.");
        }
    }

    public void deleteLikeCount(PostLikeReqDto postLikeReqDto) {

        Long postId = postLikeReqDto.getPostId();
        Long memberId = postLikeReqDto.getMemberId();

        postLikeMapper.deleteLikeCount(postId, memberId);
        redisTemplate.opsForValue().decrement("post:likes:" + postId);
    }

    public Long getLikeCount(Long postId) {
        String likeObj = redisTemplate.opsForValue().get("post:likes:" + postId);

        return likeObj != null ? Long.valueOf(likeObj) : postLikeMapper.getLikeCount(postId);
    }

    public List<PostPreviewResDto> getMemberLikedPost(Long memberId) {
        List<Long> postIds = postLikeMapper.getMemberLikedPost(memberId);
        List<PostPreviewResDto> postPreviews = postMapper.getPostPreview(postIds);
        List<String> likeCounts = getAllLikeCount(postPreviews);

        for (int i = 0; i < postPreviews.size(); i++) {
            String likeCountStr = likeCounts.get(i);
            Long likeCount = (likeCountStr != null) ? Long.parseLong(likeCountStr) : 0L;
            postPreviews.get(i).setLikeCount(likeCount);
        }

        return postPreviews;
    }

    public boolean isLikedByMember(Long postId, Long memberId) {
        return postLikeMapper.getLikeByPostAndMember(postId, memberId);
    }
}
