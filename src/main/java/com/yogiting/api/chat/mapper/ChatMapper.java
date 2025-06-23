package com.yogiting.api.chat.mapper;

import com.yogiting.api.chat.domain.ChatRoom;
import com.yogiting.api.chat.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    void createChatRoom(ChatRoom chatRoom);
    void createChatParticipant(@Param("roomId") Long roomId, @Param("memberIds") List<Long> memberIds);
    void deleteChatParticipant(@Param("roomId") Long roomId, @Param("memberId") Long memberId);
    List<ChatRoomResDto> getChatRooms(Long memberId);
    List<ChatDirectResDto> getChatDirect(Long memberId);
    List<ChatMemberResDto> getChatMember();
    ChatRoomInfoResDto getRoomInfo(Long roomId);
    Long findDirectChatRoomId(@Param("member1") Long member1, @Param("member2") Long member2);
    Boolean isRoomParticipant(@Param("memberId") Long memberId, @Param("roomId") Long roomId);
    void saveMessage(ChatMessageDto chatMessageDto);
    List<ChatMessageDto> getChatHistory(Long roomId);

}
