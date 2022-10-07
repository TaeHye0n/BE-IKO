package com.iko.iko.service.productDetails;

import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetProductExplainImageService {
    private final ProductDetailsRepository productDetailsRepository;

    public List<String> getProductExplainImage(Integer productId){
        return productDetailsRepository.getExplainImageByProductId(productId);
    }
}