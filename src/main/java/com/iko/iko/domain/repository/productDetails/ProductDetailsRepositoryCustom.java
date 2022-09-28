package com.iko.iko.domain.repository.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import org.springframework.data.domain.Pageable;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import java.util.List;

public interface ProductDetailsRepositoryCustom {

    List<ProductDetailsResponse.MainProduct> getMainProduct(Pageable pageable);

    List<ProductDetailsResponse.ProductMainByOption> getProductByOption
            (ProductDetailsRequest.ProductOptionForRequest productByOption);

}
