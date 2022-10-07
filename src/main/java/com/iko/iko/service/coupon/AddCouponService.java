package com.iko.iko.service.coupon;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Coupon;
import com.iko.iko.domain.entity.LinkMemberCoupon;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.coupon.CouponRepository;
import com.iko.iko.domain.repository.linkMemberCoupon.LinkMemberCouponRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.*;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddCouponService {

    private final LinkMemberCouponRepository linkMemberCouponRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public String addCoupon(AddCouponRequest addCouponRequest){
        Member member = validateLoginStatus();
        Optional<Coupon> coupon = couponRepository.findById(addCouponRequest.getCouponId());
        if(coupon.isPresent()){
            List<LinkMemberCoupon> linkMemberCouponList
                    = linkMemberCouponRepository.getLinkMemberCouponList(member.getMemberId(), addCouponRequest.getCouponId());
            if(linkMemberCouponList.size() == 0){
                LinkMemberCoupon linkMemberCoupon = addCouponRequest.toEntity();
                linkMemberCoupon.setCouponId(addCouponRequest.getCouponId());
                linkMemberCoupon.setMemberId(member.getMemberId());
            }
        }
        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
