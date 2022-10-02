package com.iko.iko.domain.repository.event;

import com.iko.iko.controller.event.dto.response.EventResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import static com.iko.iko.domain.entity.QEvent.event;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QLinkEventImage.linkEventImage;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EventResponse.EventMain> getEventMain(Pageable pageable){
        return jpaQueryFactory
                .select(Projections.constructor(EventResponse.EventMain.class,
                        event.eventId,
                        event.eventTitle,
                        image.imageUrl))
                .from(event)
                .join(linkEventImage).on(event.eventId.eq(linkEventImage.eventId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkEventImage.imageId)).fetchJoin()
                .where(image.imageType.eq(1))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public EventResponse.EventDetails getEventDetails(Integer selectedEventId) {
        return (EventResponse.EventDetails) jpaQueryFactory
                .select(Projections.constructor(EventResponse.EventDetails.class,
                        event.eventTitle,
                        event.eventDescription,
                        event.eventStartTime,
                        event.eventEndTime,
                        image.imageUrl))
                .from(event)
                .join(linkEventImage).on(event.eventId.eq(linkEventImage.eventId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkEventImage.imageId)).fetchJoin()
                .where(event.eventId.eq(selectedEventId))
                .where(image.imageType.eq(2))
                .fetch();
    }
}
