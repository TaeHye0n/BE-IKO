package com.iko.iko.domain.service;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.product.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Override
    public List<Product> selectAllProducts(){

        List<Product> productsList = selectAllProducts();

        return productsList;
    }

}
