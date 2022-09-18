package com.iko.iko.domain.controller;

import java.util.List;

import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository repository;

    ProductController(ProductRepository repository){
        this.repository=repository;
    }

    @GetMapping("/product")
    List<Product> all(){
        return repository.findAll();
    }
}
