package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyReplyListResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QOrder.order;
import static com.iko.iko.domain.entity.QLinkOrderDetails.linkOrderDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;
import static com.iko.iko.domain.entity.QReply.reply;
import static com.iko.iko.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Long updateInfo(
            UpdateInfoRequestDto requestDto, Member member
    ){
        return jpaQueryFactory
                .update(QMember.member)
                .set(QMember.member.address, requestDto.getAddress())
                .set(QMember.member.detailAddress, requestDto.getDetailAddress())
                .set(QMember.member.name, requestDto.getName())
                .set(QMember.member.birthday, requestDto.getBirthday())
                .set(QMember.member.phone, requestDto.getPhone())
                .set(QMember.member.readname, requestDto.getReadname())
                .set(QMember.member.postCode, requestDto.getPostCode())
                .where(QMember.member.memberId.eq(member.getMemberId()))
                .execute();

    }

    @Override
    public Long logout(
            Member member
    ){
        return jpaQueryFactory
                .update(QMember.member)
                .setNull(QMember.member.refreshToken)
                .where(QMember.member.memberId.eq(member.getMemberId()))
                .execute();
    }

    @Override
    public Long addPoint(
            Integer memberId, Integer point
    ){
        return jpaQueryFactory
                .update(member)
                .set(member.point, member.point.add(point))
                .where(member.memberId.eq(memberId))
                .execute();
    }

    @Override
    public Long minusPoint(
            Integer memberId, Integer point
    ){
        return jpaQueryFactory
                .update(member)
                .set(member.point, member.point.add(-point))
                .where(member.memberId.eq(memberId))
                .execute();
    }



}
