package com.yogiting.api.common.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.yogiting.api.common.dto.PresignedUrlResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    // presigned URL 생성
    public String generatePresignedUrl(String fileName, String folder, String contentType) {
        try {
            String key = folder + "/" + fileName;

            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key)
                    .withMethod(HttpMethod.PUT)
                    .withContentType(contentType)
                    .withExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)));

            URL presignedUrl = s3Client.generatePresignedUrl(request);
            System.out.println("Presigned URL: " + presignedUrl);
            return presignedUrl.toString();
        } catch (Exception e) {
            throw new RuntimeException("Presigned URL 생성 실패",e);
        }
    }

    public void removeFile(List<String> removeFileNames, String folder) {
        for (String r : removeFileNames) {
            String key = folder + "/" + r;
            s3Client.deleteObject(bucketName, key);
        }
    }


}
