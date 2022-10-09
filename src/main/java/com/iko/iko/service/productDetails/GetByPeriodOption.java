package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetByPeriodOption {

    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsResponse.ByPeriodOptionList getByPeriodOption(Integer productId,Integer period){
        List<ProductDetailsResponse.ByPeriodOption> pl=productDetailsRepository.getPeriodOption(productId,period);

        HashSet<String> colorCodeSet=new HashSet<>();
        HashSet<Float> graphicDiameterSet=new HashSet<>();

        for(ProductDetailsResponse.ByPeriodOption subResult : pl){
            colorCodeSet.add(subResult.getColorCode());
            graphicDiameterSet.add(subResult.getGraphicDiameter());
        }
        List<String> colorCodeList=new ArrayList<>(colorCodeSet);
        List<Float> graphicDiameterList=new ArrayList<>(graphicDiameterSet);

        ProductDetailsResponse.ByPeriodOptionList result=new ProductDetailsResponse.ByPeriodOptionList(
                colorCodeList,
                graphicDiameterList
        );
        return result;
    }
}
