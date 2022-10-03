package com.iko.iko.domain.repository.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import java.util.List;

public interface ProductDetailsRepositoryCustom {

    List<ProductDetailsResponse.GetColorCodeAndImageUrl> getColorAndImage(Integer selectedProductDetailsId,Integer selectedProductId);
    List<ProductDetailsResponse.GetGraphicDiameter> getGraphic(Integer selectedProductId);
    List<ProductDetailsResponse.MainProduct> getMainProduct(Pageable pageable, Integer productId);

    List<ProductDetailsResponse.ProductMainByOption> getProductByOption
            (ProductDetailsRequest.ProductOptionForRequest productByOption);

    List<ProductDetailsResponse.ProductDetails> getProductDetails(Integer selectedProductId);
}
