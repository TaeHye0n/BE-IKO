package com.iko.iko.domain.repository.linkOrderDetails;

import com.iko.iko.domain.entity.LinkOrderDetails;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QLinkOrderDetails.linkOrderDetails;

@Repository
@RequiredArgsConstructor
public class LinkOrderDetailsRepositoryImpl implements LinkOrderDetailsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long cancelLinkOrder(
            Integer orderId
    ){
        return jpaQueryFactory
                .delete(linkOrderDetails)
                .where(linkOrderDetails.orderId.eq(orderId))
                .execute();
    }

    @Override
    public List<LinkOrderDetails> findLinkOrderDetails(
            Integer orderId
    ){
        return jpaQueryFactory
                .selectFrom(linkOrderDetails)
                .where(linkOrderDetails.orderId.eq(orderId))
                .fetch();
    }

    @Override
    public Long deleteLinkOrder(Integer productDetailsId){
        return jpaQueryFactory
                .delete(linkOrderDetails)
                .where(linkOrderDetails.productDetailsId.eq(productDetailsId))
                .execute();
    }
}
