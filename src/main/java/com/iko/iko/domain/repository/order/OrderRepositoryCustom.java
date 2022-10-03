package com.iko.iko.domain.repository.order;

public interface OrderRepositoryCustom {

    Long cancelOrder(Integer memberId, Integer orderId);
}
