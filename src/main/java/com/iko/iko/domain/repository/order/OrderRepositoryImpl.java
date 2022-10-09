package com.iko.iko.domain.repository.order;

import com.iko.iko.controller.order.dto.request.OrderRequestDto.UpdateOrderStatusRequest;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iko.iko.domain.entity.QOrder.order;
import static com.iko.iko.domain.entity.QProduct.product;
import static com.iko.iko.domain.entity.QProductDetails.productDetails;
import static com.iko.iko.domain.entity.QLinkOrderDetails.linkOrderDetails;
import static com.iko.iko.domain.entity.QLinkProductDetailsImage.linkProductDetailsImage;
import static com.iko.iko.domain.entity.QImage.image;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long cancelOrder(
            Integer memberId, Integer orderId
    ) {
        return jpaQueryFactory
                .delete(order)
                .where(order.orderId.eq(orderId)
                        .and(order.memberId.eq(memberId)))
                .execute();
    }


    @Override
    public List<GetOrderInfoResponse> GetOrderInfoForUser(
            Integer memberId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetOrderInfoResponse.class,
                        order.createdAt,
                        order.orderId,
                        order.status,
                        order.totalPrice,
                        order.method,
                        order.name,
                        order.phone,
                        order.email,
                        order.receiverName,
                        order.postCode,
                        order.destination,
                        order.detailDestination,
                        order.receiverPhone,
                        order.message,
                        order.point,
                        order.couponId
                ))
                .distinct()
                .from(order)
                .where(order.memberId.eq(memberId))
                .orderBy(order.createdAt.desc())
                .fetch();
    }

    @Override
    public List<GetOrderInfoResponse> GetOrderInfoForAnonymous(
            Integer memberId, Integer orderId, String orderer, String ordererEmail
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetOrderInfoResponse.class,
                        order.createdAt,
                        order.orderId,
                        order.status,
                        order.totalPrice,
                        order.method,
                        order.name,
                        order.phone,
                        order.email,
                        order.receiverName,
                        order.postCode,
                        order.destination,
                        order.detailDestination,
                        order.receiverPhone,
                        order.message,
                        order.point,
                        order.couponId
                ))
                .distinct()
                .from(order)
                .where(order.memberId.eq(memberId)
                        .and(order.orderId.eq(orderId))
                        .and(order.name.eq(orderer))
                        .and(order.email.eq(ordererEmail)))
                .fetch();

    }

    @Override
    public List<GetProductForOrderResponse> GetProductForOrder(
            Integer orderId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetProductForOrderResponse.class,
                        product.productId,
                        productDetails.productDetailsId,
                        product.name,
                        productDetails.color,
                        productDetails.colorCode,
                        productDetails.graphicDiameter,
                        productDetails.degree,
                        linkOrderDetails.pcs,
                        productDetails.detailsPrice,
                        productDetails.period,
                        image.imageUrl,
                        product.discount
                ))
                .from(order)
                .join(linkOrderDetails).on(order.orderId.eq(linkOrderDetails.orderId)).fetchJoin()
                .join(productDetails).on(linkOrderDetails.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .join(linkProductDetailsImage).on(productDetails.productDetailsId.eq(linkProductDetailsImage.productDetailsId)).fetchJoin()
                .join(image).on(linkProductDetailsImage.imageId.eq(image.imageId)
                        .and(image.imageType.eq(1))).fetchJoin()
                .where(order.orderId.eq(orderId))
                .distinct()
                .orderBy(product.productId.asc())
                .fetch();
    }

    @Override
    public Long minusStockForOrder(
            Integer productDetailsId, Integer pcs
    ) {
        return jpaQueryFactory
                .update(productDetails)
                .set(productDetails.productDetailsStock, productDetails.productDetailsStock.add(-pcs))
                .where(productDetails.productDetailsId.eq(productDetailsId))
                .execute();
    }

    @Override
    public Long plusStockForOrder(
            Integer productDetailsId, Integer pcs
    ) {
        return jpaQueryFactory
                .update(productDetails)
                .set(productDetails.productDetailsStock, productDetails.productDetailsStock.add(pcs))
                .where(productDetails.productDetailsId.eq(productDetailsId))
                .execute();

    }

    @Override
    public List<GetOrderInfoResponse> getAllOrderInfo() {
        return jpaQueryFactory
                .select(Projections.constructor(GetOrderInfoResponse.class,
                        order.createdAt,
                        order.orderId,
                        order.status,
                        order.totalPrice,
                        order.method,
                        order.name,
                        order.phone,
                        order.email,
                        order.receiverName,
                        order.postCode,
                        order.destination,
                        order.detailDestination,
                        order.receiverPhone,
                        order.message,
                        order.point,
                        order.couponId
                ))
                .distinct()
                .from(order)
                .fetch();
    }

    @Override
    public List<String> getProductName(
            Integer orderId
    ) {
        return jpaQueryFactory
                .select(product.name)
                .from(order)
                .join(linkOrderDetails).on(order.orderId.eq(linkOrderDetails.orderId)).fetchJoin()
                .join(productDetails).on(linkOrderDetails.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .where(order.orderId.eq(orderId))
                .distinct()
                .fetch();
    }

    @Override
    public List<GetProductInfoForAdminResponse> getProductInfoForAdmin(
            Integer orderId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetProductInfoForAdminResponse.class,
                        product.productId,
                        product.name,
                        product.series,
                        product.discount,
                        product.manufacturer,
                        product.diameter
                ))
                .from(order)
                .join(linkOrderDetails).on(order.orderId.eq(linkOrderDetails.orderId)).fetchJoin()
                .join(productDetails).on(linkOrderDetails.productDetailsId.eq(productDetails.productDetailsId)).fetchJoin()
                .join(product).on(productDetails.productIdFk.eq(product.productId)).fetchJoin()
                .where(order.orderId.eq(orderId))
                .distinct()
                .fetch();
    }

    @Override
    public List<GetProductDetailsInfoForAdminResponse> getProductDetailsInfoForAdmin(
            Integer productId, Integer orderId
    ) {
        return jpaQueryFactory
                .select(Projections.constructor(GetProductDetailsInfoForAdminResponse.class,
                        productDetails.color,
                        productDetails.colorCode,
                        linkOrderDetails.pcs,
                        productDetails.period,
                        productDetails.productDetailsId,
                        productDetails.graphicDiameter,
                        productDetails.degree,
                        productDetails.moisture,
                        productDetails.detailsPrice,
                        productDetails.material,
                        productDetails.basecurve
                ))
                .from(productDetails)
                .join(linkOrderDetails).on(productDetails.productDetailsId.eq(linkOrderDetails.productDetailsId).
                        and(linkOrderDetails.orderId.eq(orderId))).fetchJoin()
                .where(productDetails.productIdFk.eq(productId))
                .fetch();
    }

    @Override
    public Long updateOrderStatus(
            UpdateOrderStatusRequest updateOrderStatusRequest
    ) {
        return jpaQueryFactory
                .update(order)
                .set(order.status, updateOrderStatusRequest.getStatus())
                .where(order.orderId.eq(updateOrderStatusRequest.getOrderId()))
                .execute();
    }

}
