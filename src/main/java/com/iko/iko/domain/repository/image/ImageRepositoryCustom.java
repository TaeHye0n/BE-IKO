package com.iko.iko.domain.repository.image;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepositoryCustom {
    List<ProductDetailsResponse.typeAndImage> getTypeAndImageByProductDetailsId
            (Integer productDetailsId);
    List<String> getImageUrl(Integer productId, String color, Integer period);
    List<String> getExplanationImageUrl(Integer productId, String color, Integer period);
    List<String> getBannerImage();
}
