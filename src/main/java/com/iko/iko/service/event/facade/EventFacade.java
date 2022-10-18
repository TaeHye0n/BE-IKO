package com.iko.iko.service.event.facade;

import com.iko.iko.controller.event.dto.EventRequest.AddEventRequest;
import com.iko.iko.controller.event.dto.EventResponse;
import com.iko.iko.service.event.AddEventService;
import com.iko.iko.service.event.DeleteEventService;
import com.iko.iko.service.event.GetEventDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.service.event.GetEventMainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventFacade {
    private final GetEventMainService getEventMainService;

    private final AddEventService addEventService;
    private final GetEventDetailsService getEventDetailsService;
    private final DeleteEventService deleteEventService;

    @Transactional(readOnly = true)
    public EventResponse.EventMainResponse
    getEventMain(){
        return getEventMainService.GetEventMain();
    }

    @Transactional(readOnly = true)
    public List<EventResponse.EventDetails>
    getEventDetails(Integer selectedEventId){
        return getEventDetailsService.GetEventDetails(selectedEventId);
    }

    @Transactional
    public String addEvent(AddEventRequest addEventRequest){
        return addEventService.addEvent(addEventRequest);
    }

    @Transactional
    public String deleteEvent(Integer eventId){
        return deleteEventService.deleteEvent(eventId);
    }

}
