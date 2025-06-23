package com.yogiting.api.chat.controller;

import com.yogiting.api.chat.dto.*;
import com.yogiting.api.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/room")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateReqDto chatRoomCreateReqDto) {

        Long roomId = chatService.createChatRoom(chatRoomCreateReqDto);
        System.out.println("==============");
        System.out.println(roomId);
        System.out.println("==============");
        return ResponseEntity.ok(roomId);
    }

    @GetMapping("/room")
    public ResponseEntity<?> getChatRooms(@RequestParam(required = false) Long memberId) {
        List<ChatRoomResDto> rooms = chatService.getChatRooms(memberId);
        System.out.println("==============");
        System.out.println(rooms);
        System.out.println("==============");
        return ResponseEntity.ok().body(rooms);
    }

    @GetMapping("/direct")
    public ResponseEntity<?> getChatDirect(@RequestParam Long memberId) {
        List<ChatDirectResDto> directs = chatService.getChatDirect(memberId);
        System.out.println("=============");
        System.out.println(directs);
        System.out.println("=============");
        return ResponseEntity.ok().body(directs);

    }

    @PostMapping("/direct")
    public ResponseEntity<?> createChatDirect(@RequestBody ChatRoomCreateReqDto chatRoomCreateReqDto) {
        Long roomId = chatService.createChatRoom(chatRoomCreateReqDto);
        return ResponseEntity.ok().body(roomId);
    }

    @GetMapping("/direct/exist")
    public ResponseEntity<?> existChatDirect(@RequestParam Long member1, @RequestParam Long member2) {
        Long roomId = chatService.findDirectChatRoomId(member1, member2);

        Map<String, Object> response = new HashMap<>();
        response.put("exist", roomId != null);
        if (roomId != null) {
            response.put("roomId", roomId);
        }
        System.out.println("==================");
        System.out.println(response);
        System.out.println("==================");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/member")
    public ResponseEntity<?> getChatMember() {
        List<ChatMemberResDto> members = chatService.getChatMember();
        return  ResponseEntity.ok().body(members);
    }

    @GetMapping("/roomInfo")
    public ResponseEntity<?> getRoomInfo(@RequestParam Long roomId) {
        ChatRoomInfoResDto response = chatService.getRoomInfo(roomId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getChatHistory(@RequestParam Long roomId) {
        List<ChatMessageDto> response = chatService.getChatHistory(roomId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/join/{roomId}")
    public ResponseEntity<?> joinChatRoom(@PathVariable Long roomId, @RequestBody Map<String, Long> map) {
        Long memberId = map.get("memberId");
        chatService.createChatParticipant(roomId, List.of(memberId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/leave/{roomId}")
    public ResponseEntity<?> leaveChatRoom(@PathVariable Long roomId, @RequestBody Map<String, Long> map) {
        Long memberId = map.get("memberId");
        chatService.deleteChatParticipant(roomId, memberId);
        return ResponseEntity.ok().build();
    }
}
