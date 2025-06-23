package com.yogiting.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileUpdateReqDto {
    private Long memberId;
    private String nickname;
    private String password;
    private String phone;
    private String newProfileImageUrl;
    private String oldProfileImageUrl;
}
