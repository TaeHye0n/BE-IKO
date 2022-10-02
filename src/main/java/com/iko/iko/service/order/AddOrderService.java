package com.iko.iko.service.order;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;


import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest.AddOrderDetailsRequest;
import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Order;
import com.iko.iko.domain.repository.linkOrderDetails.LinkOrderDetailsRepository;
import com.iko.iko.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddOrderService {

    private final OrderRepository orderRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;

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
            if(newLinkOrderDetails.getLinkOrderDetailsId() == null){
                throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
            }
        }

        return "Ok";
    }
}
