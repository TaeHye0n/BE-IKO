package com.iko.iko.service.event;

import com.iko.iko.controller.event.dto.EventResponse;
import com.iko.iko.domain.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetEventMainService {
    private final EventRepository eventRepository;

    public EventResponse.EventMainResponse GetEventMain(){

        List<EventResponse.EventMain> eventMain=eventRepository.getEventMain();

        Integer totalCount=eventMain.size();

        EventResponse.EventMainResponse result=new EventResponse.EventMainResponse(
          totalCount,
          eventMain
        );

        return result;
    }
}
