package com.yogiting.api.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentReqDto {
    private Long postId;
    private Long memberId;
    private String content;
}
