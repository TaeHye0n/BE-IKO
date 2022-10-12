package com.iko.iko.service.image;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Image;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class AddBannerImageService{
    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public String addBannerImage(String imageUrl){
        Member member = validateLoginStatus();
        Image image = imageRepository.save(
                Image.builder()
                .imageUrl(imageUrl)
                .imageType(4).build());
        return "배너 이미지 등록 완료";
    }
    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
