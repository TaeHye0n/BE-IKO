package com.iko.iko.controller.ProductDetails;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.service.reply.facade.ReplyFacade;
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
@RequestMapping("/productDetails")
public class ProductDetailsController {
    private final ProductDetailsFacade productDetailsFacade;
    private final ReplyFacade replyFacade;


    @PostMapping("/byOption")
    public ResponseEntity<Response<List<ProductDetailsResponse.ProductMainByOptionResponse>>>
    getProductByOption(
            @RequestBody ProductDetailsRequest.ProductOptionForRequest productOption
    ) {
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductByOption(productOption),
                        "상품 불러오기 완료"
                )
        );
    }


    @PostMapping("/main")
    public ResponseEntity<Response<ProductDetailsResponse.ProductDetailsForResponse>>

    getProductDetails(
            @RequestParam(value = "productId") Integer selectedProductId,
            @RequestParam(value = "memberId") Integer memberId
    ) {
        return ResponseEntity.ok(
                Response.of(
                productDetailsFacade.getProductDetails(selectedProductId, memberId),
                        "상세상품 불러오기 완료"
           )
        );
    }

    @GetMapping("/forRandom")
    public ResponseEntity<Response<List<ProductDetailsResponse.MainProductForResponse>>>
    getProductForRandom(
            @RequestParam(value="productId")Integer selectedProductId,
            @RequestParam(value="memberId")Integer memberId
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductForRandom(selectedProductId,memberId),
                "랜덤상품 4개 불러오기 완료"
                )
        );
    }

    @GetMapping("/explainImage")
    public ResponseEntity<Response<List<String>>> getExplainImage(
            @RequestParam(value="productId") Integer productId
    ){
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductExplainImage(productId),
                        "상품설명 이미지 리스트 불러오기 완료"
                )//
        );
    }
    @GetMapping("replyList")
    public ResponseEntity<Response<ReplyResponseDtO.ReplyInfoForResponse>> getReplyData(
            @RequestParam(value="productId") Integer productId
    ){
        return ResponseEntity.ok(
                Response.of(
                        replyFacade.getReplyInfoByProductId(productId),
                        "리뷰 정보 불러오기 완료"
                )
        );
    }

}

