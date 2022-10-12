package com.iko.iko.controller.product;

import java.util.List;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.service.product.facade.ProductFacade;
import com.iko.iko.service.productDetails.facade.ProductDetailsFacade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import com.iko.iko.common.response.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductDetailsFacade productDetailsFacade;

    @GetMapping("/main")
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

    @PostMapping("/byOption")
    public ResponseEntity<Response<ProductDetailsResponse.MainFilterProductData>>
    getProductByOption(
            @RequestParam Integer memberId,
            @RequestBody ProductDetailsRequest.ProductOptionForRequest productOption
    ) {
        return ResponseEntity.ok(
                Response.of(
                        productDetailsFacade.getProductByOption(productOption,memberId),
                        "상품 불러오기 완료"
                )
        );
    }

    @GetMapping("/filterList")
    public ResponseEntity<Response<ProductResponse.productFilter>>
    getFilterInfo(){
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getFilterInfo(),
                        "상품 필터목록 불러오기 완료"
                )
        );
    }

    @GetMapping("/recommend")
    public ResponseEntity<Response<List<ProductResponse.recommendedProduct>>>
    getRecommendProduct(){
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getRecommendProduct(),
                        "추천상품 불러오기 완료"
                )
        );
    }

    @GetMapping("/searchName")
    public ResponseEntity<Response<ProductDetailsResponse.MainFilterProductData>>
    getProductBySearching(
            @RequestParam (value = "keyWord") String searchName,
            @RequestParam (value = "memberId") Integer memberId
    ){
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getProductBySearchName(searchName,memberId),
                        "검색 상품 불러오기 완료"
                )
        );
    }

    @GetMapping("/newProduct")
    public ResponseEntity<Response<ProductDetailsResponse.MainFilterProductData>>
    getNewestProduct(
            @RequestParam (value="memberId") Integer memberId
    )
    {
        return ResponseEntity.ok(
                Response.of(
                        productFacade.getNewProduct(memberId),
                        "최근 한달내에 등록된 상품 불러오기 완료"
                )
        );
    }

}
