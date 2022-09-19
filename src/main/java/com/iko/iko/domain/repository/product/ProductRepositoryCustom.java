package com.iko.iko.domain.repository.product;

import com.iko.iko.domain.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProductRepositoryCustom{

   List<Product> findAllInnerFetchJoin();

}
