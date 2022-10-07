package com.iko.iko.controller.admin;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.event.dto.EventRequest.AddEventRequest;
import com.iko.iko.service.event.facade.EventFacade;
import com.iko.iko.service.product.facade.ProductFacade;
import com.iko.iko.controller.product.dto.request.ProductRequest.ProductSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductFacade productFacade;
    private final EventFacade eventFacade;

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
}
