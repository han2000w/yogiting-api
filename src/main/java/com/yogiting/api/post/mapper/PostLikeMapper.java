package com.yogiting.api.post.mapper;

import com.yogiting.api.post.dto.PostLikeReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostLikeMapper {

    boolean getLikeByPostAndMember(@Param("postId") Long postId, @Param("memberId") Long memberId);
    Long getLikeCount(Long postId);
    void addLikeCount(@Param("postId") Long postId, @Param("memberId") Long memberId);
    void deleteLikeCount(@Param("postId") Long postId, @Param("memberId") Long memberId);
    List<Long> getMemberLikedPost(Long memberId);
}
