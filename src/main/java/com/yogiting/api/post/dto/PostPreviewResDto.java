package com.yogiting.api.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPreviewResDto {

    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String memberNickname;
    private String memberProfileImage;
    private LocalDateTime createdAt;
    private Long viewCount;
    private Long likeCount;
    private Long commentCount;
}
