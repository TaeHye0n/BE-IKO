package com.iko.iko.service.favor;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.favor.dto.request.DeleteFavorRequestDto;
import com.iko.iko.domain.entity.Favor;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.Favor.FavorRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFavorService {

    private final MemberRepository memberRepository;
    private final FavorRepository favorRepository;

    @Transactional
    public Long deleteFavor(DeleteFavorRequestDto requestDto){
        Member member = validateLoginStatus();
        return favorRepository.deleteFavor(requestDto.getProductId(), member.getMemberId());
    }
    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
