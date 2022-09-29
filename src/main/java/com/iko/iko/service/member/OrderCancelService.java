package com.iko.iko.service.member;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCancelService {

    private final MemberRepository memberRepository;

    public Long orderCancel(Integer orderId){
        Member member = validateLoginStatus();
        return memberRepository.orderCancel(member, orderId);
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
