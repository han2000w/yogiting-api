package com.yogiting.api.post.dto;

import com.yogiting.api.common.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateReqDto {

    private Long memberId;
    private String memberNickname;
    private String title;
    private String content;
    private List<FileDto> attachedFiles;
}