package com.iko.iko.controller.ProductDetails.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDetailsRequest {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductOptionForRequest{

        private List<Integer> period;

        private List<Float> graphicDiameter;

        private List<String> colorCode;

        private List<String> series;

        private List<String> feature;


    }
}
