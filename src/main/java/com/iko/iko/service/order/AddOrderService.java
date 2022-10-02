package com.iko.iko.service.order;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;


import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest.AddOrderDetailsRequest;
import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Order;
import com.iko.iko.domain.repository.coupon.CouponRepository;
import com.iko.iko.domain.repository.linkOrderDetails.LinkOrderDetailsRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddOrderService {

    private final OrderRepository orderRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;
    private final MemberRepository memberRepository;

    private final CouponRepository couponRepository;

    @Transactional
    public String addOrder(AddOrderRequest addOrderRequest){
        Order order = orderRepository.save(addOrderRequest.toEntity());
        if(order.getOrderId() == null){
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }

        for(AddOrderDetailsRequest addOrderDetailsRequest : addOrderRequest.getAddOrderDetailsRequestList()){
            LinkOrderDetails linkOrderDetails = addOrderDetailsRequest.toEntity();
            linkOrderDetails.setOrderId(order.getOrderId());
            LinkOrderDetails newLinkOrderDetails = linkOrderDetailsRepository.save(linkOrderDetails);
            if(newLinkOrderDetails.getLinkId() == null){
                throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
            }
        }

        // 비회원인 경우
        if(addOrderRequest.getMemberId() != 0){
            memberRepository.addPoint(addOrderRequest.getMemberId(), addOrderRequest.getPoint());
            if(addOrderRequest.getCouponId() != 0){

            }
        }

        return "Ok";
    }
}
