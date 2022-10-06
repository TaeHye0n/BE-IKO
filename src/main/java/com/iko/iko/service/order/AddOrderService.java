package com.iko.iko.service.order;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;


import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest.AddOrderDetailsRequest;
import com.iko.iko.domain.entity.LinkOrderDetails;
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

@RequiredArgsConstructor
@Service
public class AddOrderService {

    private final OrderRepository orderRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;
    private final MemberRepository memberRepository;
    private final LinkMemberCouponRepository linkMemberCouponRepository;

    @Transactional
    public Integer addOrder(AddOrderRequest addOrderRequest) {
        Order order = orderRepository.save(addOrderRequest.toEntity());
        if (order.getOrderId() == null) {
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }

        for (AddOrderDetailsRequest addOrderDetailsRequest : addOrderRequest.getProducts()) {
            LinkOrderDetails linkOrderDetails = addOrderDetailsRequest.toEntity();
            linkOrderDetails.setOrderId(order.getOrderId());
            LinkOrderDetails newLinkOrderDetails = linkOrderDetailsRepository.save(linkOrderDetails);
            if (newLinkOrderDetails.getLinkId() == null) {
                throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
            }
            orderRepository.minusStockForOrder(addOrderDetailsRequest.getProductDetailsId(), addOrderDetailsRequest.getPcs());
        }
        // 회원인 경우
        if (!addOrderRequest.getMemberId().equals(0)) {
            Member member = validateLoginStatus();
            if (addOrderRequest.getMemberId().equals(member.getMemberId())) {
                memberRepository.addPoint(addOrderRequest.getMemberId(), addOrderRequest.getPoint());
                if (!addOrderRequest.getCouponId().equals(0)) {
                    linkMemberCouponRepository.setStatusUsed(addOrderRequest.getMemberId(), addOrderRequest.getCouponId());
                }
            } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }
        return order.getOrderId();
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
