package com.iko.iko.service.product.facade;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.service.product.GetProductMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductFacade {
    private final GetProductMainService getProductMainService;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse.ProductMainResponse> GetMainProduct(
            Pageable pageable){
        return getProductMainService.getMainProduct(pageable);
    }

}
