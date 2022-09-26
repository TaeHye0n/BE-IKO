package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMainProductService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductDetailsForResponse> GetMainProduct(Pageable pageable) {
        return productDetailsRepository.getMainProduct(pageable);

    }

}
