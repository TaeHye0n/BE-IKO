package com.iko.iko.domain.repository.linkProductDetailsImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;

@Repository
@RequiredArgsConstructor
public class LinkProductDetailsImageRepositoryCustomImpl implements LinkProductDetailsImageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long deleteLinkProductDetailsImage(
            Integer imageId
    ) {
        return jpaQueryFactory
                .delete(linkProductDetailsImage)
                .where(linkProductDetailsImage.imageId.eq(imageId))
                .execute();

    }

    @Override
    public List<Integer> searchImageIdByProductId(
            Integer productId
    ){
        return jpaQueryFactory
                .select(linkProductDetailsImage.imageId)
                .from(productDetails)
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .where(productDetails.productIdFk.eq(productId))
                .fetch();
    }
}
