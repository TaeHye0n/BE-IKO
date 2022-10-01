package com.iko.iko.service.cart;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.cart.dto.response.CartListResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.Cart.CartRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartListService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<CartListResponseDto> cartList(){
        Member member = validateLoginStatus();
        return cartRepository.cartList(member.getMemberId());
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
