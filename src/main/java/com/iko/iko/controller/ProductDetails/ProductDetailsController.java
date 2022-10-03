package com.iko.iko.controller.ProductDetails;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iko.iko.service.productDetails.facade.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class ProductDetailsController {
    private final ProductDetailsFacade productDetailsFacade;


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

    @PostMapping("/productDetails")
    @ApiOperation(value="제품 상세정보",notes="id= ? , productId")
    public ResponseEntity<Response<List<ProductDetailsResponse.ProductDetailsForResponse>>>
    getProductDetails(
            @RequestParam(value ="id") Integer selectedProductId
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductDetails(selectedProductId),
                        "상품 상세정보 불러오기 완료"
                )
        );
    }


}
