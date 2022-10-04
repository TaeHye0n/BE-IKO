package com.iko.iko.domain.repository.favor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.iko.iko.controller.favor.dto.response.FavorResponseDto.*;

import java.util.List;

import static com.iko.iko.domain.entity.QFavor.favor;
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;

@Repository
@RequiredArgsConstructor
public class FavorRepositoryImpl implements FavorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long deleteFavor(
            Integer productId, Integer memberId
    ) {
        return jpaQueryFactory
                .delete(favor)
                .where(favor.productId.eq(productId)
                        .and(favor.memberId.eq(memberId)))
                .execute();
    }

    @Override
    public List<GetProductInfoForFavorResponse> getProductInfoForFavor(
            Integer memberId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetProductInfoForFavorResponse.class,
                        product.productId,
                        product.name,
                        product.price,
                        product.discount
                ))
                .from(favor)
                .join(product).on(favor.productId.eq(product.productId)).fetchJoin()
                .where(favor.memberId.eq(memberId))
                .distinct()
                .orderBy(product.productId.asc())
                .fetch();
    }

    @Override
    public List<GetGraphicDiameterForFavorResponse> getGraphicDiameterForFavor(
            Integer productId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetGraphicDiameterForFavorResponse.class,
                        productDetails.graphicDiameter
                ))
                .from(product)
                .join(productDetails).on(product.productId.eq(productDetails.productIdFk)).fetchJoin()
                .where(product.productId.eq(productId))
                .distinct()
                .orderBy(productDetails.graphicDiameter.asc())
                .fetch();
    }

    @Override
    public List<GetColorAndImageUrlForFavorResponse> getColorAndImageUrlForFavor(
            Integer productId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetColorAndImageUrlForFavorResponse.class,
                        productDetails.color,
                        productDetails.colorCode,
                        image.imageUrl
                ))
                .from(productDetails)
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)
                        .and(image.imageType.eq(1))).fetchJoin()
                .where(productDetails.productIdFk.eq(productId))
                .distinct()
                .fetch();
    }

}
