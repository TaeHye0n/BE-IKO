package com.iko.iko.controller.cart.dto.request;

import com.iko.iko.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCartRequestDto {

    private Integer productDetailsId;

    @Builder
    public Cart toEntity(){
        return Cart.builder()
                .productDetailsId(productDetailsId)
                .build();
    }

}
