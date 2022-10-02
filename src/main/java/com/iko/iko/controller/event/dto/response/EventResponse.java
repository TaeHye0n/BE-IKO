package com.iko.iko.controller.event.dto.response;

import lombok.*;

import java.util.Date;

public class EventResponse {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EventMain{
        private Integer eventId;
        private String imageUrl;
        private String eventTitle;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class EventDetails{

        private String eventTitle;
        private String description;
        private Date startTime;
        private Date endTime;
        private String imageUrl;
    }
}
