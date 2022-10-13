package com.iko.iko.domain.repository.linkOrderDetails;

import com.iko.iko.domain.entity.LinkOrderDetails;

import java.util.List;

public interface LinkOrderDetailsRepositoryCustom {

    Long cancelLinkOrder(Integer orderId);

    List<LinkOrderDetails> findLinkOrderDetails(Integer orderId);

    Long deleteLinkOrder(Integer productDetailsId);
}
