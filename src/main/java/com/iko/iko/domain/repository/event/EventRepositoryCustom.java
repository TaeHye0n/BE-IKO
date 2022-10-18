package com.iko.iko.domain.repository.event;

import com.iko.iko.controller.event.dto.EventResponse;

import java.util.List;
public interface EventRepositoryCustom {
    List<EventResponse.EventMain> getEventMain();

    List<EventResponse.EventDetails> getEventDetails(Integer selectedEventId);

    Long deleteEvent(Integer eventId);
}
