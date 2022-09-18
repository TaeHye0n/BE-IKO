package com.iko.iko.domain.repository.product;

import  com.iko.iko.domain.entity.Product;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.iko.iko.domain.entity.QProduct.product;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllInnerFetchJoin(){

        return jpaQueryFactory
                .selectFrom(product)
                .fetch();

    }

}
