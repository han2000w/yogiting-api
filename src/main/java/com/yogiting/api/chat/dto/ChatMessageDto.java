package com.yogiting.api.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long senderId;
    private String senderEmail;
    private String senderNickname;
    private String senderProfileImageUrl;
    private String content;
    private LocalDateTime createdAt;
    private Long roomId;

}
