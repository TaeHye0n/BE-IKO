package com.iko.iko.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private T data;
    private int status;
    private String message;

    public static Response of(String message) {
        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }

    public static <T> Response<T> of(T data, String message) {
        return (Response<T>) Response.builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }
}
