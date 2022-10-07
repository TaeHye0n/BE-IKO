package com.iko.iko.service.favor.facade;

import com.iko.iko.controller.favor.dto.request.AddFavorRequestDto;
import com.iko.iko.controller.favor.dto.request.DeleteFavorRequestDto;
import com.iko.iko.controller.favor.dto.response.FavorResponseDto.GetFavorResponse;
import com.iko.iko.service.favor.AddFavorService;
import com.iko.iko.service.favor.DeleteFavorService;
import com.iko.iko.service.favor.GetFavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavorFacade {

    private final AddFavorService addFavorService;
    private final DeleteFavorService deleteFavorService;
    private final GetFavorService getFavorService;

    @Transactional
    public String addFavor(AddFavorRequestDto requestDto){
        return addFavorService.addFavor(requestDto);
    }

    @Transactional
    public Long deleteFavor(DeleteFavorRequestDto requestDto){
        return deleteFavorService.deleteFavor(requestDto);
    }

    @Transactional(readOnly = true)
    public List<GetFavorResponse> getFavor(){
        return getFavorService.getFavor();
    }
}
