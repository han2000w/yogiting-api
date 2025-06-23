package com.yogiting.api.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResDto {

    private Long id;
    private Long memberId;
    private String memberNickname;
    private String memberProfileImage;
    private String content;
    private LocalDateTime createdAt;
}
