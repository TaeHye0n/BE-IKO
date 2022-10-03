package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMainProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductDetailsForResponse> GetProductDetails(Integer selectedProductId) {
        List<ProductDetailsResponse.ProductDetailsForResponse> result=new ArrayList<>();

        return result;
    }
}
