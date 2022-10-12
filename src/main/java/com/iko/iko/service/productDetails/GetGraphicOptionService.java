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

    public ProductDetailsResponse.DegreeAndStockResponse GetGraphicOption(Integer productId,Integer period, String colorCode, Float graphic){
        List<ProductDetailsResponse.DegreeAndStock> subResult
                =productDetailsRepository.getGraphicOption(productId,period,colorCode,graphic);

        ProductDetailsResponse.DegreeAndStockResponse result = new ProductDetailsResponse.DegreeAndStockResponse
                (
                        subResult
                );

        return result;
    }
}
