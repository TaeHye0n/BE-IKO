package com.iko.iko.service.order.facade;

import com.iko.iko.controller.order.dto.request.OrderRequestDto.CancelOrder;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.AddOrderRequest;
import com.iko.iko.service.order.AddOrderService;
import com.iko.iko.service.order.CancelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderFacade {

    private final AddOrderService addOrderService;
    private final CancelOrderService cancelOrderService;

    @Transactional
    public String addOrder(AddOrderRequest addOrderRequest){
        return addOrderService.addOrder(addOrderRequest);
    }

    @Transactional
    public String cancelOrder(CancelOrder cancelOrder){
        return cancelOrderService.cancelOrder(cancelOrder);
    }

}
