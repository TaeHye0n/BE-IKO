package com.iko.iko.domain.repository.linkEventImage;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QLinkEventImage.linkEventImage;
import static com.iko.iko.domain.entity.QEvent.event;

@Repository
@RequiredArgsConstructor
public class LinkEventImageRepositoryImpl implements LinkEventImageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long deleteLinkEventImage(Integer eventId){
        return jpaQueryFactory
                .delete(linkEventImage)
                .where(linkEventImage.eventId.eq(eventId))
                .execute();
    }

    @Override
    public List<Integer> findImageIdByEventId(Integer eventId){
        return jpaQueryFactory
                .select(linkEventImage.imageId)
                .from(linkEventImage)
                .where(linkEventImage.eventId.eq(eventId))
                .distinct()
                .fetch();
    }
}
