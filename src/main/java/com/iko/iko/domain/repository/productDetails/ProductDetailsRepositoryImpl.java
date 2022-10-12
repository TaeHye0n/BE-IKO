package com.iko.iko.domain.repository.productDetails;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.controller.product.dto.ProductResponse.stockListResponse;
import com.iko.iko.controller.product.dto.request.ProductRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.jdi.FloatValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QLinkOrderDetails.linkOrderDetails;


@Repository
@RequiredArgsConstructor
public class ProductDetailsRepositoryImpl implements ProductDetailsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ProductDetailsResponse.ProductDetailsFilterList> getDetailsFilterInfo() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.ProductDetailsFilterList.class,
                        productDetails.period,
                        productDetails.graphicDiameter,
                        productDetails.colorCode
                ))
                .from(productDetails)
                .distinct()
                .where(productDetails.degree.eq(Float.valueOf(0)))
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.GetColorCodeAndImageUrl> getColorAndImage(Integer selectedProductId) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.GetColorCodeAndImageUrl.class,
                        productDetails.colorCode,
                        image.imageUrl
                ))
                .from(productDetails)
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(productDetails.productIdFk.eq(selectedProductId))
                .where(image.imageType.eq(1))
                .where(productDetails.period.eq(30))
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.GetGraphicDiameter> getGraphic(Integer selectedProductId) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.GetGraphicDiameter.class,
                        productDetails.graphicDiameter))
                .from(productDetails)
                .where(productDetails.productIdFk.eq(selectedProductId))
                .orderBy(productDetails.graphicDiameter.asc())
                .distinct()
                .fetch();

    }

    @Override
    public List<ProductDetailsResponse.MainProduct> getMainProduct(Pageable pageable, Integer productId) {

        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.MainProduct.class,
                        productDetails.productDetailsId
                ))
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    @Override
    public List<Integer> getProductIdByMainOption(
            ProductDetailsRequest.ProductOptionForRequest productByOption) {
        return jpaQueryFactory
                .select(product.productId)
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .where(product.productId.eq(productDetails.productIdFk))
                .where(convertStringWhere(productByOption.getColorCode(), "Color")
                        .and(convertFloatWhere(productByOption.getGraphicDiameter()))
                        .and(convertIntegerWhere(productByOption.getPeriod()))
                        .and(convertStringWhere(productByOption.getSeries(), "Series"))
                        .and(convertStringWhere(productByOption.getFeature(), "Feature")))
                .distinct()
                .fetch();

    }

    @Override
    public List<Integer> getProductDetailsIdByProductIdForBest(
            Integer productId
    ) {
        return jpaQueryFactory
                .select(linkOrderDetails.productDetailsId)
                .from(linkOrderDetails)
                .join(productDetails).on(productDetails.productDetailsId.eq(linkOrderDetails.productDetailsId)).fetchJoin()
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .fetch();


    }

    private BooleanBuilder convertIntegerWhere(List<Integer> integerList) {
        BooleanBuilder builder = new BooleanBuilder();
        for (Integer tmp : integerList) {
            builder.or(productDetails.period.eq(tmp));
        }
        return builder;
    }

    private BooleanBuilder convertStringWhere(List<String> stringList, String columnType) {
        BooleanBuilder builder = new BooleanBuilder();
        for (String tmp : stringList) {
            builder.or(columnType.equals("Color") ? productDetails.colorCode.eq(tmp) : (columnType.equals("Series") ? product.series.eq(tmp) : product.feature.eq(tmp)));
        }
        return builder;
    }

    private BooleanBuilder convertFloatWhere(List<Float> floatList) {
        BooleanBuilder builder = new BooleanBuilder();
        for (Float tmp : floatList) {
            builder.or(productDetails.graphicDiameter.eq(tmp));
        }
        return builder;
    }

    @Override
    public List<ProductDetailsResponse.ProductDetails> getProductDetails(Integer selectedProductId) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ProductDetails.class,
                        productDetails.colorCode,
                        productDetails.degree,
                        productDetails.graphicDiameter,
                        productDetails.period))
                .from(productDetails)
                .distinct()
                .where(productDetails.productIdFk.eq(selectedProductId))
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.typeAndImage> getTypeAndImageForProductDetailsId(Integer selectedProductDetailsId) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.typeAndImage.class,
                        image.imageType,
                        image.imageUrl))
                .from(productDetails)
                .join(linkProductDetailsImage).on(linkProductDetailsImage.productDetailsId.eq(selectedProductDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(productDetails.productDetailsId.eq(selectedProductDetailsId))
                /*
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(productDetails.productIdFk.eq(selectedProductId))
                .where(image.imageType.eq(1))
                .where(productDetails.period.eq(30))
                */
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.ListInfoForProductDetails> getListInfoForDetails(Integer selectedProductDetailsId) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDetailsResponse.ListInfoForProductDetails.class,
                        productDetails.period,
                        productDetails.colorCode,
                        productDetails.graphicDiameter,
                        productDetails.degree))
                .from(productDetails)
                .where(productDetails.productDetailsId.eq(selectedProductDetailsId))
                .distinct()
                .fetch();

    }

    @Override
    public List<Integer> getProductByProductOption(ProductDetailsRequest.ProductOptionForRequest productByOption) {
        return jpaQueryFactory
                .select(productDetails.productIdFk)
                .from(productDetails)
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(product.productId.eq(productDetails.productDetailsId))
                .where(convertStringWhere(productByOption.getColorCode(), "Color")
                        .or(convertFloatWhere(productByOption.getGraphicDiameter()))
                        .or(convertIntegerWhere(productByOption.getPeriod()))
                        .or(convertStringWhere(productByOption.getSeries(), "Series"))
                        .or(convertStringWhere(productByOption.getFeature(), "Feature")))
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.typeAndImage> getTypeAndImageByProductId(Integer selectedProductId) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.typeAndImage.class,
                        image.imageType,
                        image.imageUrl
                ))
                .from(productDetails)
                .join(linkProductDetailsImage).on(linkProductDetailsImage.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(productDetails.productIdFk.eq(selectedProductId))
                .distinct()
                .fetch();
    }

    @Override
    public List<String> getExplainImageByProductId(Integer productId) {
        return jpaQueryFactory
                .select(image.imageUrl)
                .from(productDetails)
                .join(linkProductDetailsImage).on(linkProductDetailsImage.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkProductDetailsImage.imageId)).fetchJoin()
                .where(image.imageType.eq(3))
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.ByPeriodOption> getPeriodOption(Integer productId, Integer period) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.ByPeriodOption.class,
                        productDetails.colorCode,
                        productDetails.graphicDiameter
                ))
                .from(productDetails)
                .where(productDetails.period.eq(period))
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .fetch();
    }

    @Override
    public List<Float> getColorCodeOption(Integer productId, Integer period, String colorCode) {
        return jpaQueryFactory
                .select(productDetails.graphicDiameter)
                .from(productDetails)
                .where(productDetails.period.eq(period))
                .where(productDetails.colorCode.eq(colorCode))
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .fetch();
    }

    @Override
    public List<ProductDetailsResponse.DegreeAndStock> getGraphicOption(Integer productId, Integer period, String colorCode, Float graphic) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.DegreeAndStock.class,
                        productDetails.degree,
                        productDetails.productDetailsStock))
                .from(productDetails)
                .where(productDetails.period.eq(period))
                .where(productDetails.colorCode.eq(colorCode))
                .where(productDetails.graphicDiameter.like(graphic.toString()))
                .where(productDetails.productIdFk.eq(productId))
                .distinct().
                fetch();
    }

    @Override
    public Integer getProductDetailsIdByOption(
            ProductDetailsRequest.ProductDetailsForRequest request
    ) {
        return jpaQueryFactory
                .select(productDetails.productDetailsId)
                .from(productDetails)
                .where(productDetails.productIdFk.eq(request.getProductId()))
                .where(productDetails.graphicDiameter.like(request.getGraphicDiameter().toString()))
                .where(productDetails.degree.eq(request.getDegree()))
                .where(productDetails.colorCode.eq(request.getColorCode()))
                .where(productDetails.period.eq(request.getPeriod()))
                .fetchOne();
    }

    @Override
    public ProductDetailsResponse.ProductDetailsByOption getProductDetailsByProductDetailsId(
            Integer productDetailsId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.ProductDetailsByOption.class,
                        product.name,
                        productDetails.color,
                        product.price,
                        productDetails.detailsPrice
                ))
                .from(productDetails)
                .join(product).on(product.productId.eq(productDetails.productIdFk)).fetchJoin()
                .where(productDetails.productDetailsId.eq(productDetailsId))
                .fetchOne();
    }

    @Override
    public List<ProductResponse.ProductDetailsInfo> getProductDetailsForAdmin(
            Integer productId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(ProductResponse.ProductDetailsInfo.class,
                        productDetails.graphicDiameter,
                        productDetails.basecurve,
                        productDetails.color,
                        productDetails.colorCode,
                        productDetails.material,
                        productDetails.detailsPrice,
                        productDetails.moisture,
                        productDetails.isSale,
                        productDetails.detailsExposure,
                        productDetails.period
                ))
                .from(productDetails)
                .distinct()
                .where(productDetails.productIdFk.eq(productId))
                .fetch();
    }

    @Override
    public List<Float> getDegreeForAdmin(
            Integer productId, String color
    ){
        return jpaQueryFactory
                .select(productDetails.degree)
                .from(productDetails)
                .distinct()
                .where(productDetails.productIdFk.eq(productId)
                        .and(productDetails.color.eq(color)))
                .fetch();
    }

    @Override
    public Long deleteProductDetails(
            Integer productId
    ){
        return jpaQueryFactory
                .delete(productDetails)
                .where(productDetails.productIdFk.eq(productId))
                .execute();
    }

    @Override
    public List<Integer> searchProductDetailsIdByProductId(
            Integer productId
    ){
        return jpaQueryFactory
                .select(productDetails.productDetailsId)
                .from(productDetails)
                .where(productDetails.productIdFk.eq(productId))
                .fetch();
    }

    @Override
    public List<stockListResponse> getStockAndDegree(
            Integer productId, String color, Integer period, Float graphicDiameter
    ){
       return jpaQueryFactory.select(Projections.constructor(stockListResponse.class,
               productDetails.degree,
               productDetails.productDetailsStock,
               productDetails.productDetailsId
               ))
               .from(productDetails)
               .where(productDetails.productIdFk.eq(productId)
                       .and(productDetails.color.eq(color))
                       .and(productDetails.period.eq(period))
                       .and(productDetails.graphicDiameter.like(graphicDiameter.toString())))
               .fetch();
    }
}
