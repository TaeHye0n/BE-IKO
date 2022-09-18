package com.iko.iko.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.iko.iko.domain.entity.Product;

@Service
public class ProductService {

    List<Product> selectAllProducts();

}
