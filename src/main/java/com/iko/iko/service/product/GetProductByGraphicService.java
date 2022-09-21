package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductByGraphicService {
    private  final ProductRepository productRepository;
/*
    public List<Product> getProductByGraphic(float graphicDiameter){
        return productRepository
                .findByGraphicDiameter(graphicDiameter);

    }
    */
}
