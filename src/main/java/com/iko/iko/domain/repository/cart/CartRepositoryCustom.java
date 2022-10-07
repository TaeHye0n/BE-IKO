package com.iko.iko.domain.repository.cart;

import com.iko.iko.controller.cart.dto.response.CartListResponseDto;
import com.iko.iko.domain.entity.Cart;

import java.util.List;

public interface CartRepositoryCustom {

    List<CartListResponseDto> cartList(Integer memberId);

    Long deleteCart(Integer cartId, Integer memberId);

    List<Cart> getCartList(Integer memberId, Integer productDetailsId);

    Long addPcs(Cart cart);
}
