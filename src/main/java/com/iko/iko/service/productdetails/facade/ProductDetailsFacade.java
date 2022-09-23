package com.iko.iko.service.productdetails.facade;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.ProductDetails;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import com.iko.iko.service.productdetails.GetProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailsFacade {
/*
    private final ProductDetailsRepository productDetailsRepository;

    @Transactional(readOnly = true)
    public List<ProductDetailsResponse.ProductDetailsForResponse>getAllProduct(Pageable pageable, Integer productId){
        return productDetailsRepository.g(pageable,productId);
    }
    */
}
