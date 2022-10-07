package com.iko.iko.service.event;

import com.iko.iko.controller.event.dto.EventResponse;
import com.iko.iko.domain.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventDetailsService {
    private final EventRepository eventRepository;

    public List<EventResponse.EventDetails> GetEventDetails(Integer selectedEventId){
        return eventRepository.getEventDetails(selectedEventId);
    }

}
