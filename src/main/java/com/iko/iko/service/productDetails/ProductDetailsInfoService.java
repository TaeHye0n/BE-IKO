package com.iko.iko.service.productDetails;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.product.dto.ProductResponse.*;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDetailsInfoService {

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public ProductDetailsInfoResponse searchDetailsById(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            String feature = productRepository.getProductFeature(productId);
            String[] featureArray = feature.split(";");
            ProductInfoResponse productInfoResponse = new ProductInfoResponse(
                    product.get().getProductId(),
                    product.get().getName(),
                    product.get().getPrice(),
                    product.get().getSeries(),
                    product.get().getDiscount(),
                    product.get().getManufacturer(),
                    product.get().getDiameter(),
                    product.get().getRecommend(),
                    product.get().getExposure(),
                    featureArray
            );
            List<ProductDetailsInfo> productDetailsInfoList = productDetailsRepository.getProductDetailsForAdmin(productId);
            List<ProductDetailsInfoWithImage> productDetailsInfoWithImageList = new ArrayList<>();
            for (ProductDetailsInfo productDetailsInfo : productDetailsInfoList) {
                List<Float> degree = productDetailsRepository.getDegreeForAdmin(productId, productDetailsInfo.getColor());
                List<String> imageUrl = imageRepository.getImageUrl(productId, productDetailsInfo.getColor(),productDetailsInfo.getPeriod());
                List<String> explanationImageUrl = imageRepository.getExplanationImageUrl(productId, productDetailsInfo.getColor(), productDetailsInfo.getPeriod());
                ProductDetailsInfoWithImage productDetailsInfoWithImage
                        = new ProductDetailsInfoWithImage(
                        productDetailsInfo.getGraphicDiameter(),
                        productDetailsInfo.getBasecurve(),
                        productDetailsInfo.getColor(),
                        productDetailsInfo.getColorCode(),
                        productDetailsInfo.getMaterial(),
                        productDetailsInfo.getDetailsPrice(),
                        productDetailsInfo.getMoisture(),
                        productDetailsInfo.getIsSale(),
                        productDetailsInfo.getDetailsExposure(),
                        productDetailsInfo.getPeriod(),
                        productDetailsInfo.getProductDetailsStock(),
                        degree,
                        imageUrl,
                        explanationImageUrl
                );
                productDetailsInfoWithImageList.add(productDetailsInfoWithImage);
            }
            ProductDetailsInfoResponse productDetailsInfoResponse = new ProductDetailsInfoResponse(productInfoResponse, productDetailsInfoWithImageList);
            return productDetailsInfoResponse;
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
    }
}
