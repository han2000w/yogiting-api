package com.yogiting.api.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogiting.api.chat.domain.ChatRoom;
import com.yogiting.api.chat.dto.*;
import com.yogiting.api.chat.mapper.ChatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatService {

    private final ChatMapper chatMapper;
    private final ObjectMapper objectMapper;

    public ChatService(ChatMapper chatMapper, ObjectMapper objectMapper) {
        this.chatMapper = chatMapper;
        this.objectMapper = objectMapper;
    }

    public Long createChatRoom(ChatRoomCreateReqDto chatRoomCreateReqDto) {

        ChatRoom chatRoom = ChatRoom.builder()
                .name(chatRoomCreateReqDto.getName())
                .description(chatRoomCreateReqDto.getDescription())
                .roomType(chatRoomCreateReqDto.getRoomType())
                .createdBy(chatRoomCreateReqDto.getCreatedBy())
                .build();

        chatMapper.createChatRoom(chatRoom);
        chatMapper.createChatParticipant(chatRoom.getId(), chatRoomCreateReqDto.getParticipants());

        return chatRoom.getId();
    }

    public List<ChatRoomResDto> getChatRooms(Long memberId) {
        List<ChatRoomResDto> response = chatMapper.getChatRooms(memberId);
        return response;
    }

    public List<ChatDirectResDto> getChatDirect(Long memberId) {
        List<ChatDirectResDto> response = chatMapper.getChatDirect(memberId);

        return response;
    }

    public List<ChatMemberResDto> getChatMember() {

        List<ChatMemberResDto> response = chatMapper.getChatMember();

        return response;
    }

    public Long findDirectChatRoomId(Long member1, Long member2) {
        return chatMapper.findDirectChatRoomId(member1, member2);
    }

    public Boolean isRoomParticipant(Long memberId, Long roomId) {
        return chatMapper.isRoomParticipant(memberId, roomId);
    }

    public ChatRoomInfoResDto getRoomInfo(Long roomId) {
        return chatMapper.getRoomInfo(roomId);
    }

    public String saveMessage(Long roomId, ChatMessageDto chatMessageDto) throws JsonProcessingException {

        chatMessageDto.setRoomId(roomId);
        chatMessageDto.setCreatedAt(LocalDateTime.now());
        chatMapper.saveMessage(chatMessageDto);

        String message = objectMapper.writeValueAsString(chatMessageDto);
        return message;
    }

    public void createChatParticipant(Long roomId, List<Long> memberIds) {
        chatMapper.createChatParticipant(roomId, memberIds);
    }

    public void deleteChatParticipant(Long roomId, Long memberId) {
        chatMapper.deleteChatParticipant(roomId, memberId);
    }

    public List<ChatMessageDto> getChatHistory(Long roomId) {
        List<ChatMessageDto> messages = chatMapper.getChatHistory(roomId);
        return messages;
    }

}
