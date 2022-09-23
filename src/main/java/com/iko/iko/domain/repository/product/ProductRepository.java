package com.iko.iko.domain.repository.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Integer >, ProductRepositoryCustom{

}
