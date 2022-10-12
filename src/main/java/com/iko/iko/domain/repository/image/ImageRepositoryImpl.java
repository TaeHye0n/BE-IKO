package com.iko.iko.domain.repository.image;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ImageRepositoryImpl implements ImageRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ProductDetailsResponse.typeAndImage> getTypeAndImageByProductDetailsId
            (Integer productDetailsId){
        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDetailsResponse.typeAndImage.class,
                        image.imageType,
                        image.imageUrl
                ))
                .from(image)
                .join(linkProductDetailsImage).on(linkProductDetailsImage.imageId.eq(image.imageId)).fetchJoin()
                .join(productDetails).on(productDetails.productDetailsId.eq(productDetailsId)).fetchJoin()
                .where(linkProductDetailsImage.productDetailsId.eq(productDetailsId)).fetchJoin()
                .distinct()
                .fetch();
    }

    @Override
    public List<String> getImageUrl(
            Integer productId, String color, Integer period
    ){
        return jpaQueryFactory
                .select(image.imageUrl)
                .from(productDetails)
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)).fetchJoin()
                .distinct()
                .where(productDetails.productIdFk.eq(productId)
                        .and(productDetails.color.eq(color))
                        .and(productDetails.period.eq(period)))
                .where(image.imageType.eq(1).or(image.imageType.eq(2)))
                .fetch();
    }

    @Override
    public List<String> getExplanationImageUrl(
            Integer productId, String color ,Integer period
    ){
        return jpaQueryFactory
                .select(image.imageUrl)
                .from(productDetails)
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)).fetchJoin()
                .distinct()
                .where(productDetails.productIdFk.eq(productId)
                        .and(productDetails.color.eq(color))
                        .and(productDetails.period.eq(period)))
                .where(image.imageType.eq(3))
                .fetch();
    }
    
    @Override
    public List<String> getBannerImage(){
        return jpaQueryFactory
                .select(image.imageUrl)
                .from(image)
                .where(image.imageType.eq(4))
                .fetch();
    }
}
