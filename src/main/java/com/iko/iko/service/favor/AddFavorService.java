package com.iko.iko.service.favor;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.favor.dto.request.AddFavorRequestDto;
import com.iko.iko.domain.entity.Favor;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.favor.FavorRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddFavorService {

    private final FavorRepository favorRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long addFavor(AddFavorRequestDto requestDto) {
        Member member = validateLoginStatus();
        Favor favor = favorRepository.save(Favor.builder()
                .memberId(member.getMemberId())
                .productId(requestDto.getProductId())
                .build());

        if (favor != null) {
            return (long)1;
        } else {
            return (long) 2;
        }
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
