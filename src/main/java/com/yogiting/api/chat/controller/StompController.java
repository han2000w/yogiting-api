package com.yogiting.api.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yogiting.api.chat.dto.ChatMessageDto;
import com.yogiting.api.chat.service.ChatService;
import com.yogiting.api.chat.service.RedisPubSubService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller
public class StompController {

    private final ChatService chatService;
    private final RedisPubSubService redisPubSubService;

    public StompController(ChatService chatService, RedisPubSubService redisPubSubService) {
        this.chatService = chatService;
        this.redisPubSubService = redisPubSubService;
    }

    @MessageMapping("/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, ChatMessageDto chatMessageDto) throws JsonProcessingException {

        String message = chatService.saveMessage(roomId, chatMessageDto);
        redisPubSubService.publish("chat", message);

    }
}
