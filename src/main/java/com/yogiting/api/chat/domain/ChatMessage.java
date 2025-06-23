package com.yogiting.api.chat.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {

    private Long id;
    private Long roomId;
    private Long senderId;
    private String content;

}
