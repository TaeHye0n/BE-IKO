package com.iko.iko.domain.repository.favor;

import com.iko.iko.controller.favor.dto.response.FavorResponseDto.*;
import com.iko.iko.domain.entity.Favor;

import java.util.List;

public interface FavorRepositoryCustom {

    Long deleteFavor(Integer productId, Integer memberId);

    List<GetProductInfoForFavorResponse> getProductInfoForFavor(Integer memberId);

    List<GetGraphicDiameterForFavorResponse> getGraphicDiameterForFavor(Integer productId);

    List<GetColorAndImageUrlForFavorResponse> getColorAndImageUrlForFavor(Integer productId);

    List<Favor> getFavorList(Integer productId, Integer memberId);

}
