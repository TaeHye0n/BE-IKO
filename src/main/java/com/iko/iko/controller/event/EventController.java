package com.iko.iko.controller.event;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.event.dto.response.EventResponse;
import com.iko.iko.service.event.facade.EventFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventFacade eventFacade;

    @GetMapping("/main")
    public ResponseEntity<Response<List<EventResponse.EventMain>>>
    getEventMain(
            @RequestParam(value="page") Integer page,
            @PageableDefault(size=10, page=0)Pageable pageable
            ){
        return ResponseEntity.ok(
                Response.of(
                        eventFacade.getEventMain(pageable),
                        "이벤트 목록 불러오기 완료"
                )
        );
    }

    @GetMapping("/details")
    public EventResponse.EventDetails
    getEventDetails(
            @RequestParam(value = "eventId") Integer selectedEventId
    ){
        return eventFacade.getEventDetails(selectedEventId);
    }

}
