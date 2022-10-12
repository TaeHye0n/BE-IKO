package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AllProductInfoService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse.ProductInfoResponse> allProductInfo() {
        List<ProductResponse.ProductInfoResponse> result = new ArrayList<>();
        List<ProductResponse.ProductInfo> productInfoList
                = productRepository.getProductInfoForAdmin();
        for (ProductResponse.ProductInfo productInfo : productInfoList) {
            String feature = productRepository.getProductFeature(productInfo.getProductId());
            String[] featureArray = feature.split(";");
            ProductResponse.ProductInfoResponse productInfoResponse
                    = new ProductResponse.ProductInfoResponse(
                    productInfo.getProductId(),
                    productInfo.getProductName(),
                    productInfo.getPrice(),
                    productInfo.getSeries(),
                    productInfo.getDiscount(),
                    productInfo.getManufacturer(),
                    productInfo.getDiameter(),
                    productInfo.getRecommend(),
                    productInfo.getExposure(),
                    featureArray
            );
            result.add(productInfoResponse);
        }
        return result;
    }
}
