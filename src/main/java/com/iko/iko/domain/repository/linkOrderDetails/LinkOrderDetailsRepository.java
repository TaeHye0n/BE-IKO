package com.iko.iko.domain.repository.linkOrderDetails;

import com.iko.iko.domain.entity.LinkOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkOrderDetailsRepository extends JpaRepository<LinkOrderDetails, Integer>, LinkOrderDetailsRepositoryCustom {

    Optional<LinkOrderDetails> findByOrderId(Integer orderId);
}
