package com.iko.iko.domain.repository.cart;

import com.iko.iko.controller.cart.dto.response.CartListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QCart.cart;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CartListResponseDto> cartList(
            Integer memberId
    ){
        return jpaQueryFactory
                .select(Projections.constructor(CartListResponseDto.class,
                        product.name,
                        productDetails.productDetailsId,
                        productDetails.color,
                        productDetails.colorCode,
                        productDetails.graphicDiameter,
                        productDetails.degree,
                        productDetails.detailsPrice,
                        product.discount,
                        productDetails.period,
                        image.imageUrl,
                        productDetails.productDetailsStock
                ))
                .from(cart)
                .join(productDetails).on(cart.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)
                        .and(image.imageType.eq(1))).fetchJoin()
                .where(cart.memberId.eq(memberId))
                .distinct()
                .orderBy(cart.createdAt.asc())
                .fetch();
    }

    @Override
    public Long deleteCart(
            Integer productDetailsId, Integer memberId
    ){
        return jpaQueryFactory
                .delete(cart)
                .where(cart.memberId.eq(memberId)
                        .and(cart.productDetailsId.eq(productDetailsId)))
                .execute();
    }
}
