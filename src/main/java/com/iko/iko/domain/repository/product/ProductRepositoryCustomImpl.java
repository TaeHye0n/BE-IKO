package com.iko.iko.domain.repository.product;

import com.iko.iko.common.util.dto.DateTimeDto;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Product;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    public List<ProductResponse.productFilterList> getFilterInfo(){
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.productFilterList.class,
                        product.series,
                        product.feature
                ))
                .distinct()
                .from(product)
                .fetch();
    }

    @Override
    public Product getProductDistinctByProductId(Integer productId){
        return jpaQueryFactory
                .select(product)
                .from(product)
                .where(product.productId.eq(productId))
                .fetchOne();
    }
    @Override
    public List<ProductResponse.GetAllProductDistinct> getAllProductByProductId(Integer productId){
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.GetAllProductDistinct.class,
                        product.productId,
                        product.series,
                        product.price,
                        product.discount,
                        product.name
                ))
                .distinct()
                .from(product)
                .where(product.productId.eq(productId))
                .fetch();
    }

    @Override
    public List<Integer> getAllProductDetailsIdByProductId(Integer productId){
        return jpaQueryFactory
                .select(productDetails.productDetailsId)
                .from(productDetails)
                .distinct()
                .where(productDetails.productIdFk.eq(productId))
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
                        product.discount,
                        product.name
                ))
                .distinct()
                .from(product)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(),pageable, queryResults.getTotal());
    }
    /*
    @Override
    public Page<ProductResponse.GetAllProductDistinct> getAllNewestProduct(Pageable pageable, DateTimeDto dateTimeDto){
        QueryResults<ProductResponse.GetAllProductDistinct> queryResults
                =jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.GetAllProductDistinct.class,
                        product.productId,
                        product.series,
                        product.price,
                        product.discount,
                        product.name
                ))
                .distinct()
                .from(product)
                .where(product.createdAt.between(dateTimeDto.getStartTime(),dateTimeDto.getEndTime()))
                .orderBy(product.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(),pageable, queryResults.getTotal());
    }*/

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

    @Override
    public List<Integer> getAllProductId(){
        return  jpaQueryFactory
                .select(product.productId)
                .from(product)
                .fetch();
    }


}
