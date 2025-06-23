package com.yogiting.api.post.mapper;

import com.yogiting.api.post.domain.Post;
import com.yogiting.api.post.dto.PostDetailResDto;
import com.yogiting.api.post.dto.PostEditReqDto;
import com.yogiting.api.post.dto.PostPreviewResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    void createPost(Post post);
    PostDetailResDto getPost(Long id);
    List<PostPreviewResDto> getPostPreview(List<Long> postIds);
    void editPost(@Param("id") Long id, @Param("dto") PostEditReqDto postEditReqDto);
    void deletePost(Long id);

}
