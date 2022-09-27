package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Member;
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
import static com.iko.iko.domain.entity.QLinkOrderDetails.linkOrderDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;
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


    @Override
    public List<MyOrderListResponseDto> MyOrderList(
            Member member
    ){
        return jpaQueryFactory
                .select(Projections.constructor(MyOrderListResponseDto.class,
                        order.orderId,
                        order.status,
                        product.name,
                        productDetails.color,
                        productDetails.colorCode,
                        productDetails.graphicDiameter,
                        productDetails.degree,
                        productDetails.detailsPrice,
                        productDetails.period,
                        image.imageUrl
                ))
                .from(order)
                .join(linkOrderDetails).on(order.orderId.eq(linkOrderDetails.orderId)).fetchJoin()
                .join(productDetails).on(linkOrderDetails.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)
                        .and(image.imageType.eq(1))).fetchJoin()
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .where(order.memberId.eq(member.getMemberId()))
                .fetch();

    }

}
