package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetByColorCodeService {

    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsResponse.ByColorCodeOption getByColorCodeOption(Integer productId,Integer period, String colorCode){
        List<Float> graphicDiameterList = productDetailsRepository.getColorCodeOption(productId,period, colorCode);

        ProductDetailsResponse.ByColorCodeOption result=new ProductDetailsResponse.ByColorCodeOption(
                graphicDiameterList
        );
        return result;
    }
}
