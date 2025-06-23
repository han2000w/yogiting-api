package com.yogiting.api.post.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostViewMapper {

    void addViewCount(@Param("postId") Long postId, @Param("memberId") Long memberId);
}
