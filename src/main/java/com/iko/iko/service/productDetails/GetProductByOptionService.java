package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetProductByOptionService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductMainByOptionResponse>
    GetProductByOption (ProductDetailsRequest.ProductOptionForRequest productByOption) {
        List<ProductDetailsResponse.ProductMainByOption> productMainByOptionList = productDetailsRepository.getProductByOption(productByOption);
        List<String> imageUrlList = new ArrayList<>();
        List<String> colorCodeList = new ArrayList<>();
        List<ProductDetailsResponse.ProductMainByOptionResponse> result = new ArrayList<>();
        /*
        List<String> isBlank = new ArrayList<>();
        if(productMainByOptionList.get(0).getColorCode().isEmpty())isBlank.add("colorCode");
        if(productMainByOptionList.get(0).getSeries().isEmpty())isBlank.add("series");
        if(productMainByOptionList.get(0).getPrice().toString().isEmpty())isBlank.;
        if(productMainByOptionList.get(0).getDiscount().toString().isEmpty())isBlank=isBlank+1;
        if(productMainByOptionList.get(0).getGraphicDiameter().toString().isEmpty())isBlank=isBlank+1;
        */
        HashMap<String, List<String>> resultMap = new HashMap<>();
        HashMap<String, List<String>> subResultMap = new HashMap<>();
        resultMap.put(
                productMainByOptionList.get(0).getProductId().toString() + "-__-" +
                productMainByOptionList.get(0).getSeries() + "-__-" +
                productMainByOptionList.get(0).getPrice().toString() + "-__-" +
                productMainByOptionList.get(0).getDiscount().toString() + "-__-" +
                productMainByOptionList.get(0).getGraphicDiameter().toString(),
                imageUrlList
        );
        subResultMap.put(
                productMainByOptionList.get(0).getProductId().toString() + "-__-" +
                        productMainByOptionList.get(0).getSeries() + "-__-" +
                        productMainByOptionList.get(0).getPrice().toString() + "-__-" +
                        productMainByOptionList.get(0).getDiscount().toString() + "-__-" +
                        productMainByOptionList.get(0).getGraphicDiameter().toString(),
                colorCodeList
        );

        for(ProductDetailsResponse.ProductMainByOption productMainByOption : productMainByOptionList){
           if(resultMap.get(
                   productMainByOption.getProductId().toString() + "-__-" +
                   productMainByOption.getSeries() + "-__-" +
                   productMainByOption.getPrice().toString() + "-__-" +
                   productMainByOption.getDiscount().toString() + "-__-" +
                   productMainByOption.getGraphicDiameter().toString() ) != null
           ) {
               resultMap.get(
                       productMainByOption.getProductId().toString() + "-__-" +
                               productMainByOption.getSeries() + "-__-" +
                               productMainByOption.getPrice().toString() + "-__-" +
                               productMainByOption.getDiscount().toString() + "-__-" +
                               productMainByOption.getGraphicDiameter().toString()
               ).add(productMainByOption.getImageUrl());
           } else {
               List<String> imageUrlForMap = new ArrayList<>();
               imageUrlForMap.add(productMainByOption.getImageUrl());
               resultMap.put(
                       productMainByOption.getProductId().toString() + "-__-" +
                       productMainByOption.getSeries() + "-__-" +
                       productMainByOption.getPrice().toString() + "-__-" +
                       productMainByOption.getDiscount().toString() + "-__-" +
                       productMainByOption.getGraphicDiameter().toString() ,
                       imageUrlForMap
               );
           }
        }

        for(ProductDetailsResponse.ProductMainByOption productMainByOption : productMainByOptionList){
            if(subResultMap.get(
                    productMainByOption.getProductId().toString() + "-__-" +
                            productMainByOption.getSeries() + "-__-" +
                            productMainByOption.getPrice().toString() + "-__-" +
                            productMainByOption.getDiscount().toString() + "-__-" +
                            productMainByOption.getGraphicDiameter().toString()) != null
            ) {
                subResultMap.get(
                        productMainByOption.getProductId().toString() + "-__-" +
                                productMainByOption.getSeries() + "-__-" +
                                productMainByOption.getPrice().toString() + "-__-" +
                                productMainByOption.getDiscount().toString() + "-__-" +
                                productMainByOption.getGraphicDiameter().toString()
                ).add(productMainByOption.getColorCode());
            } else {
                List<String> colorCodeForMap = new ArrayList<>();
                colorCodeForMap.add(productMainByOption.getColorCode());
                subResultMap.put(
                        productMainByOption.getProductId().toString() + "-__-" +
                                productMainByOption.getSeries() + "-__-" +
                                productMainByOption.getPrice().toString() + "-__-" +
                                productMainByOption.getDiscount().toString() + "-__-" +
                                productMainByOption.getGraphicDiameter().toString(),
                        colorCodeForMap
                );
            }
        }


        for(String keyS : resultMap.keySet()) {
            String[] dataList = keyS.split("-__-");
            ProductDetailsResponse.ProductMainByOptionResponse checkData = new ProductDetailsResponse.ProductMainByOptionResponse(
                    Integer.parseInt(dataList[0]),
                    dataList[1],
                    Integer.parseInt(dataList[2]),
                    Integer.parseInt(dataList[3]),
                    Float.parseFloat(dataList[4]),
                    subResultMap.get(keyS),
                    resultMap.get(keyS)
            );

            result.add(checkData);
        }

        return result;
    }
}
