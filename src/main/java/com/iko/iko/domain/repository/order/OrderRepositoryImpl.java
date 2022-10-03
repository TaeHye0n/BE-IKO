package com.iko.iko.domain.repository.order;

import com.iko.iko.domain.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QOrder.order;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long cancelOrder(
            Integer memberId, Integer orderId
    ){
        return jpaQueryFactory
                .delete(order)
                .where(order.orderId.eq(orderId)
                        .and(order.memberId.eq(memberId)))
                .execute();
    }

}
