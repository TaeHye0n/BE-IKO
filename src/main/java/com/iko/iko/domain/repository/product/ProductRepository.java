package com.iko.iko.domain.repository.product;

import com.iko.iko.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer >, ProductRepositoryCustom{

}
