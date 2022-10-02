package com.iko.iko.domain.repository.favor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
