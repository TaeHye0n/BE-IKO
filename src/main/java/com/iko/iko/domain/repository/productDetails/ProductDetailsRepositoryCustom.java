package com.iko.iko.domain.repository.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailsRepositoryCustom {

    List<ProductDetailsResponse.ProductDetailsForResponse> getAllProduct(Pageable pageable, Integer selectedProductId);
}
