package com.yogiting.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileResDto {

    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String profileImageUrl;
}
