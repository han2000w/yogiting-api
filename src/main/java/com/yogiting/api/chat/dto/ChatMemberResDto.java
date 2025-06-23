package com.yogiting.api.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberResDto {

    private Long memberId;
    private String memberNickname;
    private String memberEmail;
    private String memberProfileImage;
}

