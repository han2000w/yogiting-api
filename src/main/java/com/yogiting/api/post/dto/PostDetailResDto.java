package com.yogiting.api.post.dto;

import com.yogiting.api.common.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResDto {
    private Long id;
    private Long memberId;
    private String memberNickname;
    private String memberProfileImage;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long viewCount;
    private Long likeCount;
    private List<FileDto> attachedFiles;

    private Boolean likePost;

}
