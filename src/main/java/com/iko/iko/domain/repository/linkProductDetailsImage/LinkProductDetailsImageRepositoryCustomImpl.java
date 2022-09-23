package com.iko.iko.domain.repository.linkProductDetailsImage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class LinkProductDetailsImageRepositoryCustomImpl implements
LinkProductDetailsImageRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
}
