package com.iko.iko.service.order;

import com.iko.iko.domain.repository.order.OrderRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iko.iko.controller.order.dto.response.OrderResponseDto.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchOrderByIdService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<GetProductAndDetailsInfoForAdminResponse> searchOrderById(Integer orderId){
        List<GetProductAndDetailsInfoForAdminResponse> result = new ArrayList<>();
        List<GetProductInfoForAdminResponse> getProductInfoForAdminResponseList
                = orderRepository.getProductInfoForAdmin(orderId);
        for(GetProductInfoForAdminResponse getProductInfoForAdminResponse : getProductInfoForAdminResponseList){
            String feature = productRepository.getProductFeature(getProductInfoForAdminResponse.getProductId());
            String[] featureArray = feature.split(";");
            List<GetProductDetailsInfoForAdminResponse> getProductDetailsInfoForAdminResponseList
                    = orderRepository.getProductDetailsInfoForAdmin(getProductInfoForAdminResponse.getProductId(), orderId);
            GetProductAndDetailsInfoForAdminResponse getProductAndDetailsInfoForAdminResponse
                    = new GetProductAndDetailsInfoForAdminResponse(
                    getProductInfoForAdminResponse.getProductId(),
                    getProductInfoForAdminResponse.getProductName(),
                    getProductInfoForAdminResponse.getSeries(),
                    getProductInfoForAdminResponse.getDiscount(),
                    getProductInfoForAdminResponse.getManufacturer(),
                    getProductInfoForAdminResponse.getDiameter(),
                    featureArray,
                    getProductDetailsInfoForAdminResponseList
            );
            result.add(getProductAndDetailsInfoForAdminResponse);

        }
        return result;
    }
}
