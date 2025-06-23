package com.yogiting.api.member.service;

import com.yogiting.api.member.domain.Member;
import com.yogiting.api.member.dto.MemberProfileResDto;
import com.yogiting.api.member.dto.MemberProfileUpdateReqDto;
import com.yogiting.api.member.mapper.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberProfileResDto getMemberProfile(Long memberId) {
        Member member = memberMapper.getMember(memberId);

        MemberProfileResDto memberProfileResDto = MemberProfileResDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .phone(member.getPhone())
                .profileImageUrl(member.getProfileImageUrl())
                .build();

        return memberProfileResDto;
    }

    public void updateMember(MemberProfileUpdateReqDto memberProfileUpdateReqDto) {

        if (memberProfileUpdateReqDto.getPassword() != null && !memberProfileUpdateReqDto.getPassword().trim().isEmpty()) {
            memberProfileUpdateReqDto.setPassword(passwordEncoder.encode(memberProfileUpdateReqDto.getPassword()));
        }

        memberMapper.updateMember(memberProfileUpdateReqDto);
    }
}
