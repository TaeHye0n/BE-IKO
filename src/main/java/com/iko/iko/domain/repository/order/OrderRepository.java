package com.iko.iko.domain.repository.order;

import com.iko.iko.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {

}
