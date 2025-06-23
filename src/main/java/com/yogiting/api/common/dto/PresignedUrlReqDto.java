package com.yogiting.api.common.dto;

import lombok.Data;

@Data
public class PresignedUrlReqDto {

    private String fileName;
    private String contentType;
    private String folder;
}
