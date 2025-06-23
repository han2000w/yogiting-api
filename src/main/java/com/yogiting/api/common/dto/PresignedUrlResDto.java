package com.yogiting.api.common.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PresignedUrlResDto {

    private String presignedUrl;
    private String fileName;
    private String fileUrl;
}
