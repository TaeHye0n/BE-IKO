package com.iko.iko.controller.event.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

public class EventResponse {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class EventMain{
        private Integer eventId;
        private String imageUrl;
        private String eventTitle;
        private Integer topFixed;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class EventMainResponse{
        private Integer totalCount;
        private List<EventMain> eventMainList;
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
