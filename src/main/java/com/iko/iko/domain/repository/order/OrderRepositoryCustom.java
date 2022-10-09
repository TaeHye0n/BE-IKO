package com.iko.iko.domain.repository.order;

import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;


import java.util.List;

public interface OrderRepositoryCustom {

    Long cancelOrder(Integer memberId, Integer orderId);
    List<GetOrderInfoResponse> GetOrderInfoForUser(Integer memberId);
    List<GetOrderInfoResponse> GetOrderInfoForAnonymous(Integer memberId, Integer orderId, String orderer, String ordererEmail);
    List<GetProductForOrderResponse> GetProductForOrder(Integer orderId);
    Long minusStockForOrder(Integer productDetailsId, Integer pcs);
    Long plusStockForOrder(Integer productDetailsId, Integer pcs);
    List<GetOrderInfoResponse> getAllOrderInfo();
    List<String> getProductName(Integer orderId);


}
