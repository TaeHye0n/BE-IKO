package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetProductByOptionService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductMainByOption>
    GetProductByOption (ProductDetailsRequest.ProductOptionForRequest productByOption){
        return productDetailsRepository.getProductByOption(productByOption);
    }
}
