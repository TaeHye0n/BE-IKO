package com.iko.iko.domain.Controller;

import java.util.List;

import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
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
