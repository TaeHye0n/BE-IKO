package com.iko.iko.controller.image.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ImageResponse {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor

    public static class ImageForResponse {
        private String ImageUrl;

    }
}
