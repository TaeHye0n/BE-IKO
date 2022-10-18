package com.iko.iko.domain.repository.event;

import com.iko.iko.controller.event.dto.EventResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public List<EventResponse.EventMain> getEventMain(){
        return jpaQueryFactory
                .select(Projections.constructor(EventResponse.EventMain.class,
                        event.eventId,
                        image.imageUrl,
                        event.eventTitle,
                        event.topFixed))
                .from(event)
                .join(linkEventImage).on(event.eventId.eq(linkEventImage.eventId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkEventImage.imageId)).fetchJoin()
                .where(image.imageType.eq(1))
                .fetch();
    }

    @Override
    public List<EventResponse.EventDetails> getEventDetails(Integer selectedEventId) {
        return  jpaQueryFactory
                .select(Projections.constructor(EventResponse.EventDetails.class,
                        event.eventTitle,
                        event.eventDescription,
                        event.eventStartTime,
                        event.eventEndTime,
                        image.imageUrl))
                .from(event)
                .join(linkEventImage).on(event.eventId.eq(linkEventImage.eventId)).fetchJoin()
                .join(image).on(image.imageId.eq(linkEventImage.imageId)).fetchJoin()
                .where(event.eventId.eq(selectedEventId).and(image.imageType.eq(3)))
                .fetch();
    }

    @Override
    public Long deleteEvent(Integer eventId){
        return jpaQueryFactory
                .delete(event)
                .where(event.eventId.eq(eventId))
                .execute();
    }

}
