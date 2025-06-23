package com.yogiting.api.member.mapper;

import com.yogiting.api.member.domain.Member;
import com.yogiting.api.member.dto.MemberProfileUpdateReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    Member getMember(Long memberId);
    void updateMember(@Param("reqDto") MemberProfileUpdateReqDto reqDto);

}
