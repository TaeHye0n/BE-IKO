package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest.*;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateStockService {

    private final ProductDetailsRepository productDetailsRepository;

    @Transactional
    public String updateStock(UpdateStockRequest updateStockRequest){

        List<DetailsIdAndStock> detailsIdAndStockList = updateStockRequest.getDetailsIdAndStocks();
        for(DetailsIdAndStock detailsIdAndStock : detailsIdAndStockList){
            productDetailsRepository.updateStock(detailsIdAndStock.getProductDetailsId(), detailsIdAndStock.getStock());
        }
        return "0k";
    }
}
