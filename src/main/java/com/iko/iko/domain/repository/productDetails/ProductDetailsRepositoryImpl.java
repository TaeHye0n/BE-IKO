package com.iko.iko.domain.repository.productDetails;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.LinkProductDetailsImage;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QImage.image;

@Repository
@RequiredArgsConstructor
public class ProductDetailsRepositoryImpl implements ProductDetailsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDetailsResponse.ProductDetailsForResponse> getAllProduct(Pageable pageable,Integer selectedProductId){

        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductDetailsForResponse.class,
                        productDetails.productDetailsId,
                        product,image))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.image_id.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(selectedProductId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
