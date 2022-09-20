package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.product.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductMainService {

    private final ProductRepository productRepository;
    @Transactional(readOnly = true)

    public  List<ProductResponse.ProductMainResponse> getMainProduct(Pageable pageable){
        return productRepository.getMainProduct(pageable);
    }

}
