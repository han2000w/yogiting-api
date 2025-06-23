package com.yogiting.api.post.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class Post {

    private Long id;
    private Long memberId;
    private String title;
    private String content;
}
