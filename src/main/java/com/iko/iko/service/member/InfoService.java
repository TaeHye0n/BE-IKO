package com.iko.iko.service.member;

import com.iko.iko.controller.member.dto.response.MemberResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InfoService {

   private final MemberRepository memberRepository;

    public MemberResponseDto findMyInfo() {
        Member member = validateLoginStatus();
        return MemberResponseDto.builder()
                .member(member)
                .build();
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다."));
    }
}
