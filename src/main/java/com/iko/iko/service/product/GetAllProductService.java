package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductService {
    private  final ProductRepository productRepository;

    public List<ProductResponse.AllProduct> GetAllProduct(Pageable pageable){
        return productRepository.getProduct(pageable);

    }
}