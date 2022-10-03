package com.iko.iko.domain.repository.productDetails;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public List<ProductDetailsResponse.MainProduct> getMainProduct(Pageable pageable) {

        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.MainProduct.class,
                        product.productId,
                        product.series,
                        productDetails.graphicDiameter,
                        product.price,
                        product.discount,
                        productDetails.colorCode,
                        image.imageUrl))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(productDetails.productIdFk))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
            ProductDetailsRequest.ProductOptionForRequest productByOption) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductMainByOption.class,
                        product.productId,
                        product.series,
                        product.price,
                        product.discount,
                        productDetails.graphicDiameter,
                        productDetails.colorCode,
                        image.imageUrl))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(productDetails.productDetailsId))
                .where(convertStringWhere(productByOption.getColorCode(),"Color")
                        .or(convertFloatWhere(productByOption.getGraphicDiameter()))
                        .or(convertIntegerWhere(productByOption.getPeriod()))
                        .or(convertStringWhere(productByOption.getSeries(),"Series"))
                        .or(convertStringWhere(productByOption.getFeature(),"Feature")))
                .distinct()
                .fetch();

    }
    private BooleanBuilder convertIntegerWhere(List<Integer> integerList){
        BooleanBuilder builder = new BooleanBuilder();
        for(Integer tmp: integerList){
            builder.or(productDetails.period.eq(tmp));
        }
        return builder;
    }
    private BooleanBuilder convertStringWhere(List<String> stringList,String columnType){
        BooleanBuilder builder = new BooleanBuilder();
    for(String tmp: stringList){
        builder.or(columnType.equals("Color")?productDetails.colorCode.eq(tmp):(columnType.equals("Series")?product.series.eq(tmp):product.feature.eq(tmp)));
    }
    return builder;
    }
    private BooleanBuilder convertFloatWhere(List<Float> floatList){
        BooleanBuilder builder = new BooleanBuilder();
        for(Float tmp: floatList){
            builder.or(productDetails.graphicDiameter.eq(tmp));
        }
        return builder;
    }

    @Override
    public List<ProductDetailsResponse.ProductDetails> getProductDetails(Integer selectedProductId){
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductDetails.class,
                        product.productId,
                        productDetails.productDetailsId,
                        product.name,
                        product.series,
                        product.diameter,
                        productDetails.colorCode,
                        product.price,
                        product.discount,
                        image.imageUrl,
                        image.imageType,
                        productDetails.degree,
                        productDetails.graphicDiameter))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(productDetails.productIdFk.eq(selectedProductId))
                .fetch();
    }

}
