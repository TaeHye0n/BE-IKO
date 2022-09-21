package com.iko.iko.controller.image.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class imageResponse {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class ImageUrl{
        private String ImageUrl;
    }
}
