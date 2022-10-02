package com.iko.iko.service.event;

import com.iko.iko.controller.event.dto.response.EventResponse;
import com.iko.iko.domain.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEventDetailsService {
    private final EventRepository eventRepository;

    public EventResponse.EventDetails GetEventDetails(Integer selectedEventId){
        return eventRepository.getEventDetails(selectedEventId);
    }

}
