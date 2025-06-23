package com.yogiting.api.chat.dto;

import com.yogiting.api.chat.domain.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResDto {

    private Long roomId;
    private String name;
    private String description;
    private RoomType roomType;
    private int memberCount;
    private Boolean isParticipant;

}
