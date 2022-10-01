package com.iko.iko.controller.favor;


import com.iko.iko.common.response.Response;
import com.iko.iko.controller.favor.dto.request.AddFavorRequestDto;
import com.iko.iko.controller.favor.dto.request.DeleteFavorRequestDto;
import com.iko.iko.service.favor.facade.FavorFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/favor")
@RestController
public class FavorController {

    private final FavorFacade favorFacade;

    public FavorController(FavorFacade favorFacade) {
        this.favorFacade = favorFacade;
    }

    //  즐겨찾기 추가
    @PostMapping("/add")
    public ResponseEntity<Response> addFavor(@RequestBody @Valid AddFavorRequestDto requestDto) {
        favorFacade.addFavor(requestDto);
        return ResponseEntity.ok(
                Response.of(
                        "즐겨찾기 추가 완료"));
    }

    // 즐겨찾기 상품 삭제
    @PostMapping("/delete")
    public ResponseEntity<Response> deleteFavor(@RequestBody @Valid DeleteFavorRequestDto requestDto){
        favorFacade.deleteFavor(requestDto);
        return ResponseEntity.ok(
                Response.of(
                        "즐겨찾기 상품 삭제 완료"));
    }

}
