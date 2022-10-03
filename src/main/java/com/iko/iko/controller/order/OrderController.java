package com.iko.iko.controller.order;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.*;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;
import com.iko.iko.service.order.facade.OrderFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping("/add")
    public ResponseEntity<Response<Integer>> addOrder(
            @RequestBody @Valid AddOrderRequest addOrderRequest
    ) {
        return ResponseEntity.ok(
                Response.of(orderFacade.addOrder(addOrderRequest),
                        "주문 완료"
                )
        );
    }

    @PostMapping("/cancel")
    public ResponseEntity<Response<String>> cancelOrder(
            @RequestBody @Valid CancelOrderRequest cancelOrderRequest
    ) {
        return ResponseEntity.ok(
                Response.of(orderFacade.cancelOrder(cancelOrderRequest),
                        "주문 취소 완료"
                )
        );
    }

    @PostMapping("/get")
    public ResponseEntity<Response<List<GetOrderResponse>>> getOrder(
            @RequestBody @Valid GetOrderRequest getOrderRequest
    ) {
        return ResponseEntity.ok(
                Response.of(orderFacade.getOrder(getOrderRequest),
                        "주문 조회 완료")
        );
    }

}
