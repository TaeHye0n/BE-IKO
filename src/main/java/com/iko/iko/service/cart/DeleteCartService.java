package com.iko.iko.service.cart;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.cart.dto.request.DeleteCartRequestDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.cart.CartRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    @Transactional
    public Long deleteCart(DeleteCartRequestDto requestDto){
        Member member = validateLoginStatus();
        return cartRepository.deleteCart(requestDto.getCartId(), member.getMemberId());
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
