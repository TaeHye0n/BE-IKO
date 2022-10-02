package com.iko.iko.service.order.facade;

import com.iko.iko.service.order.AddOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderFacade {

    private final AddOrderService addOrderService;

}
