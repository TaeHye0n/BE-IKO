package com.iko.iko.service.productDetails.facade;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.service.productDetails.GetMainProductService;
import com.iko.iko.service.productDetails.GetProductByOptionService;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailsFacade {

    private final GetMainProductService getMainProductService;

    private final GetProductByOptionService getProductByOptionService;

    @Transactional(readOnly = true)
    public List<ProductDetailsResponse.ProductDetailsForResponse>
    getMainProduct(Pageable pageable){
        return getMainProductService.GetMainProduct(pageable);
    }

    @Transactional(readOnly = true)
    public List<ProductDetailsResponse.ProductMainByOptionResponse>getProductByOption(
            ProductDetailsRequest.ProductOptionForRequest productByOption){
        return getProductByOptionService.GetProductByOption(productByOption);
    }

}
