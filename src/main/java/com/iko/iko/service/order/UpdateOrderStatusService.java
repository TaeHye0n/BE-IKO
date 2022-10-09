package com.iko.iko.service.order;

import com.iko.iko.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.UpdateOrderStatusRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateOrderStatusService {

    private final OrderRepository orderRepository;

    @Transactional
    public String UpdateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderRepository.updateOrderStatus(updateOrderStatusRequest);
        return "0k";
    }
}
