package com.yogiting.api.post.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostAttachment {

    private Long id;
    private Long postId;
    private String originalFilename;
    private String storedFilename;
    private String fileUrl;
    private Long fileSize;
    private String contentType;
}
