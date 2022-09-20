package com.iko.iko.domain.repository.productDetails;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductDetailsRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;


}
