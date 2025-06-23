package com.yogiting.api.common.controller;

import com.yogiting.api.common.dto.PresignedUrlReqDto;
import com.yogiting.api.common.dto.PresignedUrlResDto;
import com.yogiting.api.common.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api/file")
public class FileController {

    private final S3Service s3Service;

    public FileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/presignedUrl")
    public ResponseEntity<?> getPresignedUrl(@RequestBody PresignedUrlReqDto request) {

        System.out.println("request value: " + request);

        String s3_URL = "https://yogiting.s3.ap-northeast-2.amazonaws.com/";
        String fileName = UUID.randomUUID().toString() + "_" + request.getFileName();
        String presignedUrl = s3Service.generatePresignedUrl(fileName, request.getFolder(), request.getContentType());
        String fileUrl = s3_URL + request.getFolder() + "/" + fileName;

        PresignedUrlResDto response = PresignedUrlResDto.builder()
                .presignedUrl(presignedUrl)
                .fileName(fileName)
                .fileUrl(fileUrl)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
