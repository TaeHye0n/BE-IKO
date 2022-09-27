package com.iko.iko.controller.ProductDetails;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import com.iko.iko.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iko.iko.service.productDetails.facade.*;
import org.springframework.web.bind.annotation.GetMapping;
import com.iko.iko.service.productDetails.facade.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class ProductDetailsController {
    private final ProductDetailsFacade productDetailsFacade;

    @GetMapping("/main")
    public ResponseEntity<Response<List<ProductDetailsResponse.ProductDetailsForResponse>>>
    getMainProduct(
            @PageableDefault(size=9,page=0) Pageable pageable
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getMainProduct(pageable),
                        "모든상품 불러오기 완료"
                )
        );
    }

    @PostMapping("/productOption")
    public ResponseEntity<Response<List<ProductDetailsResponse.ProductMainByOptionResponse>>>
    getProductByOption(
            @RequestBody ProductDetailsRequest.ProductOptionForRequest productOption
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductByOption(productOption),
                        "상품 불러오기 완료"
                )
        );
    }

}
