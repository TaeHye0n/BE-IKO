package com.iko.iko.service.member;


import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyOrderListService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MyOrderListResponseDto> myOrderList() {
        Member member = validateLoginStatus();
        return memberRepository.myOrderList(member);
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
