package com.yogiting.api.chat.dto;

import com.yogiting.api.chat.domain.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomCreateReqDto {

    private String name;
    private String description;
    private RoomType roomType;
    private Long createdBy;
    private List<Long> participants;

}