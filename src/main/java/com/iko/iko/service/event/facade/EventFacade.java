package com.iko.iko.service.event.facade;

import com.iko.iko.controller.event.dto.response.EventResponse;
import com.iko.iko.service.event.GetEventDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.iko.iko.service.event.GetEventMainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventFacade {
    private final GetEventMainService getEventMainService;

    private final GetEventDetailsService getEventDetailsService;
    @Transactional(readOnly = true)
    public List<EventResponse.EventMain>
    getEventMain(Pageable pageable){
        return getEventMainService.GetEventMain(pageable);
    }

    @Transactional(readOnly = true)
    public EventResponse.EventDetails
    getEventDetails(Integer selectedEventId){
        return getEventDetailsService.GetEventDetails(selectedEventId);
    }

}
