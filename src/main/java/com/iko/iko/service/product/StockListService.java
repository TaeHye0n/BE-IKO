package com.iko.iko.service.product;

import com.iko.iko.controller.product.dto.ProductResponse.stockListResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockListService {

    private final ProductDetailsRepository productDetailsRepository;

    @Transactional(readOnly = true)
    public List<stockListResponse> stockList(Integer productId, String color, Integer period, Float graphicDiameter){
        return productDetailsRepository.getStockAndDegree(productId, color, period, graphicDiameter);
    }

}
