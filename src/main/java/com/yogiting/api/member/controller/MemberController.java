package com.yogiting.api.member.controller;

import com.yogiting.api.member.domain.Member;
import com.yogiting.api.member.dto.MemberProfileResDto;
import com.yogiting.api.member.dto.MemberProfileUpdateReqDto;
import com.yogiting.api.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getMemberProfile(Long memberId) {
        MemberProfileResDto memberProfileResDto = memberService.getMemberProfile(memberId);
        return ResponseEntity.ok(memberProfileResDto);
    }

    @PatchMapping("/profile")
    public ResponseEntity<?> updateMemberProfile(@RequestBody MemberProfileUpdateReqDto memberProfileUpdateReqDto) {
        memberService.updateMember(memberProfileUpdateReqDto);
        return ResponseEntity.ok().build();
    }
}
