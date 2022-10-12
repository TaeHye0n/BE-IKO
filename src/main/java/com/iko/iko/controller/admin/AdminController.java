package com.iko.iko.controller.admin;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.InsertCouponRequest;
import com.iko.iko.controller.event.dto.EventRequest.AddEventRequest;
import com.iko.iko.controller.order.dto.request.OrderRequestDto.*;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.service.coupon.facade.CouponFacade;
import com.iko.iko.service.event.facade.EventFacade;
import com.iko.iko.service.image.facade.ImageFacade;
import com.iko.iko.service.order.facade.OrderFacade;
import com.iko.iko.service.product.facade.ProductFacade;
import com.iko.iko.controller.product.dto.request.ProductRequest.ProductSaveRequest;
import com.iko.iko.service.productDetails.facade.ProductDetailsFacade;
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

    private final ProductDetailsFacade productDetailsFacade;

    private final ImageFacade imageFacade;


    @PostMapping("/insertProduct")
    public ResponseEntity<Response<String>> insertProduct(
            @RequestBody @Valid ProductSaveRequest productSaveRequest
    ) {
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
    ) {
        return ResponseEntity.ok(
                Response.of(
                        eventFacade.addEvent(addEventRequest),
                        "이벤트 등록 완료"
                )
        );
    }

    @PostMapping("/insertCoupon")
    public ResponseEntity<Response<Integer>> insertCoupon(
            @RequestBody @Valid InsertCouponRequest insertCouponRequest
    ) {
        return ResponseEntity.ok(
                Response.of(couponFacade.insertCoupon(insertCouponRequest),
                        "쿠폰 등록 완료")
        );
    }

    @GetMapping("/allOrderInfo")
    public ResponseEntity<Response<List<GetAllOrderResponse>>> getAllOrderInfo() {
        return ResponseEntity.ok(
                Response.of(orderFacade.getAllOrderInfo(),
                        "모든 주문 조회 완료")
        );
    }

    @GetMapping("/searchOrderById")
    public ResponseEntity<Response<List<GetProductAndDetailsInfoForAdminResponse>>> searchOrderById(
            @RequestParam(value = "orderId") Integer orderId
    ) {
        return ResponseEntity.ok(
                Response.of(orderFacade.searchOrderById(orderId),
                        "해당 주문의 상품 정보 조회 완료")
        );
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<Response<String>> updateOrderStatus(
            @RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest
    ) {
        return ResponseEntity.ok(
                Response.of(orderFacade.updateOrderStatus(updateOrderStatusRequest),
                        "주문 상태 변경 완료")
        );
    }

    @GetMapping("/allProductInfo")
    public ResponseEntity<Response<List<ProductResponse.ProductInfoResponse>>> allProductInfo(){
        return ResponseEntity.ok(
                Response.of(productFacade.allProductInfo(),
                        "모든 상품 정보 조회 완료")
        );
    }


    @GetMapping("/searchDetailsById")
    public ResponseEntity<Response<ProductResponse.ProductDetailsInfoResponse>> searchDetailsById(
            @RequestParam(value = "productId") Integer productId
    ){
        return ResponseEntity.ok(
                Response.of(productDetailsFacade.searchDetailsById(productId),
                        "상품 상세 정보 조회 완료")
         );
    }

    @GetMapping("/insertBannerImage")
    public ResponseEntity<Response<String>> insertBannerImage(
            @RequestParam (value="imageUrl") String imageUrl
    ){
        return ResponseEntity.ok(
                Response.of(
                        imageFacade.addBannerImage(imageUrl),
                        "배너이미지 등록완료")
        );
    }

}
