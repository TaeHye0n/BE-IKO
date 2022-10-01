package com.iko.iko.service.cart.facade;

import com.iko.iko.controller.cart.dto.request.AddCartRequestDto;
import com.iko.iko.controller.cart.dto.request.DeleteCartRequestDto;
import com.iko.iko.controller.cart.dto.response.CartListResponseDto;
import com.iko.iko.service.cart.AddCartService;
import com.iko.iko.service.cart.CartListService;
import com.iko.iko.service.cart.DeleteCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacade {

    private final AddCartService addCartService;
    private final CartListService cartListService;
    private final DeleteCartService deleteCartService;

    @Transactional
    public Long addCart(AddCartRequestDto requestDto){
        return addCartService.addCart(requestDto);
    }

    @Transactional(readOnly = true)
    public List<CartListResponseDto> cartList(){
        return cartListService.cartList();
    }

    @Transactional
    public Long deleteCart(DeleteCartRequestDto requestDto){
        return deleteCartService.deleteCart(requestDto);
    }

}
