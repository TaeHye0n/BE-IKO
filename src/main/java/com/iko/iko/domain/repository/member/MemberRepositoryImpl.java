package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QMember.member;
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QOrder.order;
import static com.iko.iko.domain.entity.QBaseEntity.baseEntity;
import static com.iko.iko.domain.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long updateInfo(
            UpdateInfoRequestDto requestDto
    ){
        return jpaQueryFactory
                .update(member)
                .set(member.address, requestDto.getAddress())
                .set(member.name, requestDto.getName())
                .set(member.birthday, requestDto.getBirthday())
                .set(member.phone, requestDto.getPhone())
                .set(member.readname, requestDto.getReadname())
                .where(member.memberId.eq(requestDto.getMemberId()))
                .execute();
    }


//    @Override
//    public List<MyOrderListResponseDto> MyOrderList(
//            Integer memberId
//    ){
//        return jpaQueryFactory
//                .select(Projections.constructor(MyOrderListResponseDto.class,
//                        order.orderId,
//                        order.status,
//                        product.name,
//                        productDetails.color,
//                        productDetails.colorCode,
//                        productDetails.graphicDiameter,
//                        productDetails.degree,
//                        order.totalPrice,
//                        productDetails.period
//                ))
//                .from(order)
//                .join(productDetails).on(order.detailsIdList.eq(productDetails.productDetailsId)).fetchJoin()
//
//    }

}
