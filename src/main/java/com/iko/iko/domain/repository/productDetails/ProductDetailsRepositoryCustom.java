package com.iko.iko.domain.repository.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.Product;
import org.springframework.data.domain.Pageable;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import java.util.List;

public interface ProductDetailsRepositoryCustom {

    List<ProductDetailsResponse.GetColorCodeAndImageUrl> getColorAndImage(Integer selectedProductId);
    List<ProductDetailsResponse.GetGraphicDiameter> getGraphic(Integer selectedProductId);
    List<ProductDetailsResponse.MainProduct> getMainProduct(Pageable pageable, Integer productId);

    List<ProductDetailsResponse.ProductMainByOption> getProductByOption
            (ProductDetailsRequest.ProductOptionForRequest productByOption);

    List<ProductDetailsResponse.ProductDetails> getProductDetails(Integer selectedProductId);
    List<ProductDetailsResponse.typeAndImage> getTypeAndImageForProductDetailsId(Integer selectedProductDetailsId);

    List<ProductDetailsResponse.ListInfoForProductDetails> getListInfoForDetails(Integer selectedProductDetailsId);

    List<Integer> getProductByProductOption (ProductDetailsRequest.ProductOptionForRequest productOptionForRequest);

    List<ProductDetailsResponse.typeAndImage> getTypeAndImageByProductId(Integer selectedProductId);

    List<ProductDetailsResponse.ProductDetailsFilterList> getDetailsFilterInfo();

}
