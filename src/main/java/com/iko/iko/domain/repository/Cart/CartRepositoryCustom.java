package com.iko.iko.domain.repository.Cart;

import com.iko.iko.controller.cart.dto.response.CartListResponseDto;

import java.util.List;

public interface CartRepositoryCustom {

    List<CartListResponseDto> cartList(Integer memberId);

    Long deleteCart(Integer productDetailsId, Integer memberId);
}
