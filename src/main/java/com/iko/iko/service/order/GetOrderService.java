package com.iko.iko.service.order;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.GetProductForOrderResponse;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.GetOrderInfoResponse;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.GetOrderResponse;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.GetOrderRequest;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.order.OrderRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetOrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<GetOrderResponse> getOrder(GetOrderRequest getOrderRequest) {
        if (!getOrderRequest.getMemberId().equals(0)) {
            Member member = validateLoginStatus();
            if (getOrderRequest.getMemberId().equals(member.getMemberId())) {
                List<GetOrderResponse> result = new ArrayList<>();
                List<GetOrderInfoResponse> GetOrderInfoResponseList = orderRepository.GetOrderInfoForUser(member.getMemberId());
                for (GetOrderInfoResponse getOrderInfoResponse : GetOrderInfoResponseList) {
                    List<GetProductForOrderResponse> getProductForOrderResponseList
                            = orderRepository.GetProductForOrder(getOrderInfoResponse.getOrderId());
                    GetOrderResponse getOrderResponse = new GetOrderResponse(getOrderInfoResponse, getProductForOrderResponseList);
                    result.add(getOrderResponse);
                }
                return result;

            } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);

            //비회원인 경우
        } else {
            List<GetOrderResponse> result = new ArrayList<>();
            List<GetOrderInfoResponse> GetOrderInfoResponseList
                    = orderRepository.GetOrderInfoForAnonymous(
                    getOrderRequest.getMemberId(),
                    getOrderRequest.getOrderId(),
                    getOrderRequest.getOrderer(),
                    getOrderRequest.getOrdererEmail()
            );
            for (GetOrderInfoResponse getOrderInfoResponse : GetOrderInfoResponseList) {
                List<GetProductForOrderResponse> getProductForOrderResponseList
                        = orderRepository.GetProductForOrder(getOrderInfoResponse.getOrderId());
                GetOrderResponse getOrderResponse = new GetOrderResponse(getOrderInfoResponse, getProductForOrderResponseList);
                result.add(getOrderResponse);
            }
            return result;
        }

    }


    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }


}
