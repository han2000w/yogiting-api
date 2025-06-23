package com.yogiting.api.chat.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatParticipant {

    private Long id;
    private Long roomId;
    private Long memberId;
}
