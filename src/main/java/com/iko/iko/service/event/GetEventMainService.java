package com.iko.iko.service.event;

import com.iko.iko.controller.event.dto.response.EventResponse;
import com.iko.iko.domain.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetEventMainService {
    private final EventRepository eventRepository;

    public List<EventResponse.EventMain> GetEventMain(Pageable pageable){
        return eventRepository.getEventMain(pageable);
    }
}
