package com.iko.iko.controller.admin;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.InsertCouponRequest;
import com.iko.iko.controller.event.dto.EventRequest.AddEventRequest;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.GetAllOrderResponse;
import com.iko.iko.service.coupon.facade.CouponFacade;
import com.iko.iko.service.event.facade.EventFacade;
import com.iko.iko.service.order.facade.OrderFacade;
import com.iko.iko.service.product.facade.ProductFacade;
import com.iko.iko.controller.product.dto.request.ProductRequest.ProductSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductFacade productFacade;
    private final EventFacade eventFacade;
    private final CouponFacade couponFacade;
    private final OrderFacade orderFacade;

    @PostMapping("/insertProduct")
    public ResponseEntity<Response<String>> insertProduct(
            @RequestBody @Valid ProductSaveRequest productSaveRequest
    ){
        return ResponseEntity.ok(
                Response.of(
                        productFacade.saveProductService(productSaveRequest),
                        "상품 등록 완료"
                )
        );
    }

    @PostMapping("/insertEvent")
    public ResponseEntity<Response<String>> insertEvent(
            @RequestBody @Valid AddEventRequest addEventRequest
            ){
        return ResponseEntity.ok(
                Response.of(
                        eventFacade.addEvent(addEventRequest),
                        "이벤트 등록 완료"
                )
        );
    }

    @PostMapping("/insertCoupon")
    public ResponseEntity<Response<String>> insertCoupon(
            @RequestBody @Valid InsertCouponRequest insertCouponRequest
    ){
        return ResponseEntity.ok(
                Response.of(couponFacade.insertCoupon(insertCouponRequest),
                        "쿠폰 등록 완료")
        );
    }

    @GetMapping("/allOrderInfo")
    public ResponseEntity<Response<List<GetAllOrderResponse>>> getAllOrderInfo(){
        return ResponseEntity.ok(
                Response.of(orderFacade.getAllOrderInfo(),
                        "모든 주문 조회 완료")
        );
    }

}
