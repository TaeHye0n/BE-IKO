package com.iko.iko.domain.repository.linkOrderDetails;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LinkOrderDetailsRepositoryImpl implements LinkOrderDetailsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
}
