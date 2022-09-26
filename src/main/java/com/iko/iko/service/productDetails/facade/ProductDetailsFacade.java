package com.iko.iko.service.productDetails.facade;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.service.productDetails.GetMainProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailsFacade {

    private final GetMainProductService getMainProductService;

    @Transactional(readOnly = true)
    public List<ProductDetailsResponse.ProductDetailsForResponse>getMainProduct(Pageable pageable){
        return getMainProductService.GetMainProduct(pageable);
    }

}
