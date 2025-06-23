package com.yogiting.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private Long id;
    private String originalFilename;
    private String storedFilename;
    private String fileUrl;
    private Long fileSize;
    private String contentType;
}
