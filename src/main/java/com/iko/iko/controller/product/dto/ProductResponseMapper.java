package com.iko.iko.controller.product.dto;

import com.iko.iko.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductResponseMapper {
    ProductResponseMapper INSTANCE= Mappers.getMapper(ProductResponseMapper.class);

    ProductResponse ofProduct(Product product);


}
