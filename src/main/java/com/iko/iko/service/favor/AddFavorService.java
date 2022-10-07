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

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddFavorService {

    private final FavorRepository favorRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String addFavor(AddFavorRequestDto requestDto) {
        Member member = validateLoginStatus();
        List<Favor> favorList = favorRepository.getFavorList(requestDto.getProductId(), member.getMemberId());
        if (favorList.size() == 0) {
            Favor favor = favorRepository.save(Favor.builder()
                    .memberId(member.getMemberId())
                    .productId(requestDto.getProductId())
                    .build());
            return "즐겨찾기 추가 완료";
        }
        else{
            favorRepository.deleteFavor(requestDto.getProductId(), member.getMemberId());
            return "즐겨찾기 삭제 완료";
        }
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
