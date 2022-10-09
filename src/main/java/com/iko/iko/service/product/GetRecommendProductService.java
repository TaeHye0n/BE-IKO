package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor

public class GetRecommendProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse.recommendedProduct> getRecommendedProduct(){
        List<ProductResponse.recommendedProduct> result =new ArrayList<>();

        List<ProductResponse.recommendedProduct> responseData=
                productRepository.getRecommendedProduct();

        HashSet<String> productNameList=new HashSet<>();
        for(ProductResponse.recommendedProduct rData : responseData){
            if(productNameList.contains(rData.getName()))continue;
            else {
                productNameList.add(rData.getName());
                result.add(rData);
            }
        }

        return result;
    }
}
