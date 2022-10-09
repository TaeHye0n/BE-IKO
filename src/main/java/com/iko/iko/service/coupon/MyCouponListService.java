package com.iko.iko.service.coupon;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.coupon.dto.response.CouponResponseDto.*;
import com.iko.iko.domain.entity.LinkMemberCoupon;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.coupon.CouponRepository;
import com.iko.iko.domain.repository.linkMemberCoupon.LinkMemberCouponRepository;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MyCouponListService {

    private final CouponRepository couponRepository;
    private final LinkMemberCouponRepository linkMemberCouponRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<CouponInfoListResponse> myCoupon() {
        Member member = validateLoginStatus();
        Date date = new Date();
        List<LinkMemberCoupon> linkMemberCouponList
                = linkMemberCouponRepository.getLinkMemberCouponListByMemberId(member.getMemberId());
        for (LinkMemberCoupon linkMemberCoupon : linkMemberCouponList) {
            boolean after = linkMemberCoupon.getExpiredDate().after(date);
            if (!after) {
                linkMemberCouponRepository.setStatusExpired(linkMemberCoupon.getMemberCouponId());
            }
        }
            return couponRepository.getMyCouponInfo(member.getMemberId());

    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

}
