package com.iko.iko.domain.repository.linkOrderDetails;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
