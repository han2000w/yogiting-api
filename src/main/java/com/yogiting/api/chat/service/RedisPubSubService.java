package com.yogiting.api.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogiting.api.chat.dto.ChatMessageDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisPubSubService implements MessageListener {

    private final StringRedisTemplate stringRedisTemplate;
    private final SimpMessageSendingOperations messageTemplate;
    private final ObjectMapper objectMapper;

    public RedisPubSubService(StringRedisTemplate stringRedisTemplate, SimpMessageSendingOperations messageTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.messageTemplate = messageTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);

    }

    // redis에 새로 등록된 메시지를 해당 리스너가 받음
    // 메시지를 받아서 stomp 구독자인 프론트엔드에게 전달
    @Override
    public void onMessage(Message message, byte[] pattern) {

        System.out.println("===============");
        System.out.println("redis에서 수신한 메시지: " + message);
        System.out.println("===============");

        // 메시지 객체에서 바로 파싱을 할수 없어서 먼저 String으로 변환 해줘야함
        String payload = new String(message.getBody());

        try {
            ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
            messageTemplate.convertAndSend("/v1/api/chat/ws/topic/"+chatMessageDto.getRoomId(), chatMessageDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
