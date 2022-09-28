package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMainProductService {

    private final ProductDetailsRepository productDetailsRepository;


    public List<ProductDetailsResponse.MainProduct> GetMainProduct(Pageable pageable) {
        /*List<ProductDetailsResponse.MainProduct> mainProductList=productDetailsRepository.getMainProduct(pageable);
        List<String> colorCodeList = new ArrayList<>();
        List<String> imageUrlList = new ArrayList<>();
        List<Float> graphicDiameterList = new ArrayList<>();
        
        List<ProductDetailsResponse.MainProductForResponse> result = new ArrayList<>();
        return result;*/
        return productDetailsRepository.getMainProduct(pageable);
    }

}
