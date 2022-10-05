package com.iko.iko.controller.product;

import java.util.List;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.service.product.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import com.iko.iko.common.response.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping("/product")
    public ResponseEntity<Response<List<ProductDetailsResponse.MainProductForResponse>>>
    getMainProduct(
            @RequestParam Integer page, @RequestParam Integer size,
            @RequestParam  Integer memberId

    ){
         Pageable pr =PageRequest.of(page-1,size);
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getMainProduct(pr,memberId),
                        "모든상품 불러오기 완료"
                )
        );
    }
    @PostMapping("/productByOption")
    public ResponseEntity<Response<List<ProductDetailsResponse.MainProductForResponse>>>
    getMainProductByOption(
            @RequestParam Integer page, @RequestParam Integer size,
            @RequestParam Integer memberId,
            @RequestBody ProductDetailsRequest.ProductOptionForRequest productOption

    ){
        Pageable pr =PageRequest.of(page-1,size);
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getMainProductByOption(productOption,pr,memberId),
                        "모든상품 불러오기 완료"
                )
        );
    }
}
