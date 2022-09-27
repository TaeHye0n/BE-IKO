package com.iko.iko.common.util;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.ProductDetails;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import java.util.List;
/*
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QImage.image;
*/

@Component
@RequiredArgsConstructor
public class CommonQuery {

    private final JPAQueryFactory jpaQueryFactory;


}
