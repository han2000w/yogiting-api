package com.yogiting.api.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomInfoResDto {

    private Long roomId;
    private String name;
    private int memberCount;
}
