package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGraphicOptionService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.DegreeAndStock> GetGraphicOption(Integer period, String colorCode, Float graphic){
        return productDetailsRepository.getGraphicOption(period,colorCode,graphic);
    }
}
