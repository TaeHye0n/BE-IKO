package com.iko.iko.domain.repository.product;

import com.iko.iko.common.util.dto.DateTimeDto;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.controller.product.dto.request.ProductRequest;
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.*;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QMember.member;
import static com.iko.iko.domain.entity.QFavor.favor;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.common.util.EntityListUtil.convertStringListToString;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ProductResponse.productFilterList> getFilterInfo() {
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
    public Product getProductDistinctByProductId(Integer productId) {
        return jpaQueryFactory
                .select(product)
                .from(product)
                .where(product.productId.eq(productId))
                .fetchOne();
    }

    @Override
    public List<ProductResponse.GetAllProductDistinct> getAllProductByProductId(Integer productId) {
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
    public List<Integer> getAllProductDetailsIdByProductId(Integer productId) {
        return jpaQueryFactory
                .select(productDetails.productDetailsId)
                .from(productDetails)
                .distinct()
                .where(productDetails.productIdFk.eq(productId))
                .fetch();
    }
    @Override
    public Page<ProductResponse.GetAllProductDistinct> getAllProductByFilter(Pageable pageable, Integer productId){
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
                .where(product.productId.eq(productId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(),pageable, queryResults.getTotal());
    }
    @Override
    public Page<ProductResponse.GetAllProductDistinct> getAllProduct(Pageable pageable) {
        QueryResults<ProductResponse.GetAllProductDistinct> queryResults
                = jpaQueryFactory
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
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
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
    public Long getMemberIsFavorite(Integer memberId, Integer selectedProductId) {
        return jpaQueryFactory
                .select(favor.favorId.count())
                .from(favor)
                .where(favor.memberId.eq(memberId))
                .where(favor.productId.eq(selectedProductId))
                .fetchOne();
    }

    @Override
    public List<Integer> getAllProductId() {
        return jpaQueryFactory
                .select(product.productId)
                .from(product)
                .fetch();
    }

    @Override
    public String getProductFeature(
            Integer productId
    ) {
        return jpaQueryFactory
                .select(product.feature)
                .from(product)
                .where(product.productId.eq(productId))
                .fetchOne();
    }

    @Override
    public List<ProductResponse.recommendedProduct> getRecommendedProduct() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductResponse.recommendedProduct.class,
                        product.name,
                        product.price,
                        product.discount,
                        image.imageUrl
                ))
                .from(product)
                .join(productDetails).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(productDetails.productIdFk))
                .where(image.imageType.eq(1))
                .where(product.recommend.eq(1))
                .fetch();
    }
    @Override
    public List<Integer> getProductIdBySearchName(String searchName){
        return jpaQueryFactory
                .select(product.productId)
                .from(product)
                .where(product.name.upper().contains(searchName))
                .fetch();
    }

    @Override
    public List<ProductResponse.ProductInfo> getProductInfoForAdmin() {
        return jpaQueryFactory
                .select(Projections.constructor(ProductResponse.ProductInfo.class,
                        product.productId,
                        product.name,
                        product.price,
                        product.series,
                        product.discount,
                        product.manufacturer,
                        product.diameter,
                        product.recommend,
                        product.exposure
                ))
                .from(product)
                .distinct()
                .fetch();
    }

    @Override
    public Long updateProduct(
            ProductRequest.ProductUpdateRequest productUpdateRequest
    ){
        return jpaQueryFactory
                .update(product)
                .set(product.name, productUpdateRequest.getProductName())
                .set(product.price, productUpdateRequest.getPrice())
                .set(product.discount, productUpdateRequest.getDiscount())
                .set(product.diameter, productUpdateRequest.getDiameter())
                .set(product.manufacturer, productUpdateRequest.getManufacturer())
                .set(product.series , productUpdateRequest.getSeries())
                .set(product.feature, convertStringListToString(productUpdateRequest.getFeature()))
                .set(product.recommend, productUpdateRequest.getRecommend())
                .set(product.exposure, productUpdateRequest.getExposure())
                .where(product.name.eq(productUpdateRequest.getProductName()))
                .execute();
    }

    @Override
    public Integer searchProductIdByNameForAdmin(
            String productName
    ){
        return jpaQueryFactory
                .select(product.productId)
                .from(product)
                .where(product.name.eq(productName))
                .fetchOne();
    }

    @Override
    public List<ProductResponse.ProductIdAndCreatedAt> getProductIdByNewest(){
        return jpaQueryFactory
                .select(Projections.constructor(ProductResponse.ProductIdAndCreatedAt.class,
                        product.productId,
                        product.createdAt))
                .from(product)
                .orderBy(product.createdAt.desc())
                .distinct()
                .fetch();
    }

}
