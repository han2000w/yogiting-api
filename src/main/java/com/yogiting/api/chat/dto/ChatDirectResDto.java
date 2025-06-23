package com.yogiting.api.chat.dto;

import com.yogiting.api.chat.domain.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDirectResDto {
    private Long roomId;
    private String name;
    private RoomType roomType;

    // 상대방 정보 추가
    private Long memberId;
    private String memberNickname;
    private String memberProfileImage;
    private String memberEmail;

}
