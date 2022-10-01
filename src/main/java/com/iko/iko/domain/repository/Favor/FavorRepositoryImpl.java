package com.iko.iko.domain.repository.Favor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QFavor.favor;

@Repository
@RequiredArgsConstructor
public class FavorRepositoryImpl implements FavorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long deleteFavor(
            Integer productId, Integer memberId
    ){
        return jpaQueryFactory
                .delete(favor)
                .where(favor.productId.eq(productId)
                        .and(favor.memberId.eq(memberId)))
                .execute();
    }

}
