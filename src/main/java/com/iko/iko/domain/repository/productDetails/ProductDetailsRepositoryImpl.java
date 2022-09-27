package com.iko.iko.domain.repository.productDetails;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
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
    public List<ProductDetailsResponse.ProductDetailsForResponse> getMainProduct(Pageable pageable){

        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductDetailsForResponse.class,
                        product.productId,product.series,product.feature,productDetails.graphicDiameter,
                        productDetails.colorCode,product.price,product.discount,
                        image.image_url,productDetails.period))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.image_id.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(productDetails.productIdFk))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

                /*
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
                */

    }
    @Override
    public List<ProductDetailsResponse.ProductMainByOption> getProductByOption(
            ProductDetailsRequest.ProductOptionForRequest productByOption){
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductMainByOption.class,
                        product.productId,product.series,product.price,
                        product.price,productDetails.graphicDiameter,
                        productDetails.colorCode,image.image_url))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(image).on(image.image_id.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .where(product.productId.eq(productDetails.productDetailsId))
                .where(productDetails.colorCode.eq(productByOption.getColorCode())
                        .or(productDetails.graphicDiameter.eq(productByOption.getGraphicDiameter()))
                        .or(productDetails.period.eq(productByOption.getPeriod()))
                        .or(product.series.eq(productByOption.getSeries()))
                        .or(product.feature.eq(productByOption.getFeature())))
                .distinct()
                .fetch();

    }

}
