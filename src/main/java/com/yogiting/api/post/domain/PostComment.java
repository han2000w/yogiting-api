package com.yogiting.api.post.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostComment {

    private Long id;
    private Long postId;
    private Long memberId;
    private String content;
}
