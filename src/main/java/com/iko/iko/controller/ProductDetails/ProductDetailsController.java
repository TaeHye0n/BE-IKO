package com.iko.iko.controller.ProductDetails;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ProductDetailsController {
/*
    @GetMapping("/allProduct")
    public ResponseEntity<Response<List<ProductDetailsResponse.ProductDetailsForResponse>>>
    getProduct(
            @PageableDefault(size=9,page=0) Pageable pageable,
            Integer productId
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getAllProduct(pageable,productId),
                        "모든상품 불러오기 완료"
                )
        );
    }*/
}
