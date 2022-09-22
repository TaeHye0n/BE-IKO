package com.iko.iko.domain.repository.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.controller.image.dto.imageResponse;
import  com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.entity.QImage;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QImage.image;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductResponse.ProductMainResponse> getMainProduct(
            Pageable pageable
    ){
//        return jpaQueryFactory
//        .select(Projections.constructor(ProductResponse.ProductMainResponse.class,
//                product.name, ExpressionUtils.as(
//                        JPAExpressions
//                                .select(image.image_url)
//                                .from(image)
//                                .where(image.image_id.eq(product.imageId)),
//                        "imageUrl")
//                ,product.series,product.feature,product.discount,product.price,
//                ExpressionUtils.as(
//                        JPAExpressions
//                                .select(productDetails.colorCode)
//                                .from(productDetails)
//                                .where(productDetails.productIdFk.eq(product.productId)),
//                        "colorCode"
//                )))
//                .from(product)
//                .orderBy(product.createdAt.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
  return null;

    }

    @Override
    public List<ProductResponse.AllProduct> getProduct(){

//        return jpaQueryFactory
//                .select(Projections.constructor(ProductResponse.AllProduct.class,
//                        product.name, product.price,product.discount,
//                        image.image_url
//                        ))
//                .from(product)
//                .innerJoin(image)
//                .on(product.imageId.eq(image.image_id))
//                .orderBy(product.price.asc())
//                .fetch();

       return null;
    }
}
