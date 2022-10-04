package com.iko.iko.service.order;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.CancelOrderRequest;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Order;
import com.iko.iko.domain.repository.linkMemberCoupon.LinkMemberCouponRepository;
import com.iko.iko.domain.repository.linkOrderDetails.LinkOrderDetailsRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.order.OrderRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelOrderService {

    private final OrderRepository orderRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;
    private final MemberRepository memberRepository;
    private final LinkMemberCouponRepository linkMemberCouponRepository;

    @Transactional
    public String cancelOrder(CancelOrderRequest cancelOrderRequest) {
        Optional<Order> order = orderRepository.findById(cancelOrderRequest.getOrderId());
        if (order.isPresent()) {
            if (order.get().getMemberId().equals(cancelOrderRequest.getMemberId())) {
                // 회원인 경우 포인트 마이너스, 쿠폰 반환
                if (!cancelOrderRequest.getMemberId().equals(0)) {
                    Member member = validateLoginStatus();
                    if (member.getMemberId().equals(cancelOrderRequest.getMemberId())) {
                        memberRepository.minusPoint(order.get().getMemberId(), order.get().getPoint());
                        linkMemberCouponRepository.setStatusAvailable(order.get().getMemberId(), order.get().getCouponId());
                    }
                } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);

                linkOrderDetailsRepository.cancelLinkOrder(cancelOrderRequest.getOrderId());
                orderRepository.cancelOrder(order.get().getMemberId(), cancelOrderRequest.getOrderId());
            } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
