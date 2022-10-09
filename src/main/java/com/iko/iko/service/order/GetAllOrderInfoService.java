package com.iko.iko.service.order;

import com.iko.iko.domain.repository.order.OrderRepository;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllOrderInfoService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<GetAllOrderResponse> getAllOrderInfo(){
        List<GetAllOrderResponse> result = new ArrayList<>();
        List<GetOrderInfoResponse> getOrderInfoResponseList = orderRepository.getAllOrderInfo();
        for(GetOrderInfoResponse getOrderInfoResponse : getOrderInfoResponseList){
            List<String> productName = orderRepository.getProductName(getOrderInfoResponse.getOrderId());
            GetAllOrderResponse getAllOrderResponse = new GetAllOrderResponse(getOrderInfoResponse, productName);
            result.add(getAllOrderResponse);
        }
        return result;
    }

}
