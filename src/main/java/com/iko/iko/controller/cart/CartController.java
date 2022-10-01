package com.iko.iko.controller.cart;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.cart.dto.request.AddCartRequestDto;
import com.iko.iko.controller.cart.dto.request.DeleteCartRequestDto;
import com.iko.iko.controller.cart.dto.response.CartListResponseDto;
import com.iko.iko.service.cart.facade.CartFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    private final CartFacade cartFacade;

    public CartController(CartFacade cartFacade){
        this.cartFacade = cartFacade;
    }

    // 장바구니 추가
    @PostMapping("/add")
    public ResponseEntity<Response> addCart (@RequestBody @Valid AddCartRequestDto requestDto){
        cartFacade.addCart(requestDto);
        return ResponseEntity.ok(
                Response.of(
                        "장바구니 추가 완료"));
    }

    // 장바구니 조회
    @GetMapping("list")
    public ResponseEntity<Response<List<CartListResponseDto>>> cartList() {
        return ResponseEntity.ok(
                Response.of(
                        cartFacade.cartList(),
                        "장바구니 조회 완료"));
    }

    // 장바구니 에서 상품 제거
    @PostMapping("delete")
    public ResponseEntity<Response> deleteCart(@RequestBody @Valid DeleteCartRequestDto requestDto){
        cartFacade.deleteCart(requestDto);
        return ResponseEntity.ok(
                Response.of(
                        "상품 제거 완료"));
    }
}
