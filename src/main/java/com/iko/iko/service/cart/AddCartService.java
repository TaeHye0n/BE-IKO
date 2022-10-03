package com.iko.iko.service.cart;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.cart.dto.request.AddCartRequestDto;
import com.iko.iko.domain.entity.Cart;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.cart.CartRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String addCart(AddCartRequestDto requestDto) {
        Member member = validateLoginStatus();
        Cart cart = cartRepository.save(Cart.builder()
                .memberId(member.getMemberId())
                .productDetailsId(requestDto.getProductDetailsId())
                .build());

        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
