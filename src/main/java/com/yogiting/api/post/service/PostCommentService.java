package com.yogiting.api.post.service;

import com.yogiting.api.post.domain.PostComment;
import com.yogiting.api.post.dto.PostCommentReqDto;
import com.yogiting.api.post.dto.PostCommentResDto;
import com.yogiting.api.post.dto.PostDetailResDto;
import com.yogiting.api.post.mapper.PostCommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {

    private final PostCommentMapper postCommentMapper;

    public PostCommentService(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }

    public List<PostCommentResDto> getComment(Long postId) {
        List<PostCommentResDto> comments = postCommentMapper.getComment(postId);
        return comments;
    }

    public PostCommentResDto addComment(PostCommentReqDto postCommentReqDto) {
         PostComment postComment = PostComment.builder()
                 .postId(postCommentReqDto.getPostId())
                 .memberId(postCommentReqDto.getMemberId())
                 .content(postCommentReqDto.getContent())
                 .build();

         postCommentMapper.addComment(postComment);

         PostCommentResDto comment = postCommentMapper.getCommentById(postComment.getId());

         return comment;
    }

    public void deleteComment(Long commentId) {
        postCommentMapper.deleteComment(commentId);
    }
}
