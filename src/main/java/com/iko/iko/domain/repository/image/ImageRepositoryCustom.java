package com.iko.iko.domain.repository.image;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepositoryCustom {
    List<ProductDetailsResponse.typeAndImage> getTypeAndImageByProductDetailsId
            (Integer productDetailsId);

    List<String> getBannerImage();
}
