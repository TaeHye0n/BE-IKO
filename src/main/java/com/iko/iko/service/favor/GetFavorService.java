package com.iko.iko.service.favor;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.favor.dto.response.FavorResponseDto.*;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.favor.FavorRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFavorService {

    private final FavorRepository favorRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<GetFavorResponse> getFavor() {
        Member member = validateLoginStatus();
        List<GetFavorResponse> result = new ArrayList<>();

        List<GetProductInfoForFavorResponse> getProductInfoForFavorResponseList
                = favorRepository.getProductInfoForFavor(member.getMemberId());

        for (GetProductInfoForFavorResponse getProductInfoForFavorResponse : getProductInfoForFavorResponseList) {
            List<GetGraphicDiameterForFavorResponse> getGraphicDiameterForFavorResponseList
                    = favorRepository.getGraphicDiameterForFavor(getProductInfoForFavorResponse.getProductId());
            List<Float> graphicDiameterList = new ArrayList<>();

            for(GetGraphicDiameterForFavorResponse graphicDiameter : getGraphicDiameterForFavorResponseList){
                graphicDiameterList.add(graphicDiameter.getGraphicDiameter());
            }

            List<GetColorAndImageUrlForFavorResponse> getColorAndImageUrlForFavorResponseList
                    = favorRepository.getColorAndImageUrlForFavor(getProductInfoForFavorResponse.getProductId());
            GetFavorResponse getFavorResponse
                    = new GetFavorResponse(getProductInfoForFavorResponse, graphicDiameterList, getColorAndImageUrlForFavorResponseList);
            result.add(getFavorResponse);
        }
        return result;
    }


    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }


}
