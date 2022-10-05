package com.iko.iko.domain.repository.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Product;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QMember.member;
import static com.iko.iko.domain.entity.QFavor.favor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductResponse.GetAllProductDistinct> getAllProductByProductId(Integer productId){
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.GetAllProductDistinct.class,
                        product.productId,
                        product.series,
                        product.price,
                        product.discount
                ))
                .distinct()
                .from(product)
                .where(product.productId.eq(productId))
                .fetch();
    }

    @Override
    public Page<ProductResponse.GetAllProductDistinct> getAllProduct(Pageable pageable){
        QueryResults<ProductResponse.GetAllProductDistinct> queryResults
                =jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.GetAllProductDistinct.class,
                        product.productId,
                        product.series,
                        product.price,
                        product.discount
                ))
                .distinct()
                .from(product)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(),pageable, queryResults.getTotal());
    }

    //유저의 찜정보를 가져옵니다
    @Override
    public Long getMemberIsFavorite(Integer memberId, Integer selectedProductId){
        return jpaQueryFactory
                .select(favor.favorId.count())
                .from(favor)
                .where(favor.memberId.eq(memberId))
                .where(favor.productId.eq(selectedProductId))
                .fetchOne();
    }

}
