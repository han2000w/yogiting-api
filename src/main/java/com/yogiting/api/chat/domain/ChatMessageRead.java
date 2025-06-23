package com.yogiting.api.chat.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageRead {

    private Long roomId;
    private Long memberId;
    private Long lastReadMessageId;
}
