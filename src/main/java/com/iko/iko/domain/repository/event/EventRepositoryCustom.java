package com.iko.iko.domain.repository.event;

import com.iko.iko.controller.event.dto.response.EventResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface EventRepositoryCustom {
    List<EventResponse.EventMain> getEventMain(Pageable pageable);

    EventResponse.EventDetails getEventDetails(Integer selectedEventId);
}
