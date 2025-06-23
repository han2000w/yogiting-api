package com.yogiting.api.post.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class PostLike {

    private Long id;
    private Long postId;
    private Long memberId;
}
