package com.yogiting.api.chat.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoom {

    private Long id;
    private String name;
    private String description;
    private RoomType roomType;
    private Long createdBy;
}
