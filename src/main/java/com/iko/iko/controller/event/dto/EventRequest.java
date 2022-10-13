package com.iko.iko.controller.event.dto;

import com.iko.iko.domain.entity.Event;
import com.iko.iko.domain.entity.Image;
import com.iko.iko.domain.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

public class EventRequest {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class AddEventRequest{
        private String title;
        private String description;
        private Date startTime;
        private Date endTime;
        private Integer topFixed;
        private String mainImageUrl;
        private String explainImageUrl;
        /*@Getter
        @Builder
        @Setter
        @AllArgsConstructor
        public static class AddEventImageRequest {
            private String mainImageUrl;
            private String explainImageUrl;

            @Builder
            public Image toEntity(Integer imageType, String imageUrl) {
                return Image.builder()
                        .imageType(imageType)
                        .imageUrl(imageUrl)
                        .build();
            }
        }*/

        @Builder
        public Event toEntity() {
            return Event.builder()
                    .eventTitle(title)
                    .eventDescription(description)
                    .eventStartTime(startTime)
                    .eventEndTime(endTime)
                    .topFixed(topFixed)
                    .build();
        }


    }

}
