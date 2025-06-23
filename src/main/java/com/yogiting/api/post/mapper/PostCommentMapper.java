package com.yogiting.api.post.mapper;

import com.yogiting.api.post.domain.PostComment;
import com.yogiting.api.post.dto.PostCommentReqDto;
import com.yogiting.api.post.dto.PostCommentResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCommentMapper {

    List<PostCommentResDto> getComment(Long postId);
    PostCommentResDto getCommentById(Long commentId);
    void addComment(PostComment postComment);
    void deleteComment(Long commentId);

}
