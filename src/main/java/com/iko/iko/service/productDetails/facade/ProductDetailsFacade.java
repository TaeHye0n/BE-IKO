package com.iko.iko.service.productDetails.facade;


import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.service.productDetails.*;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailsFacade {
    private final GetMainProductDetailsService getMainProductDetailsService;
    private final GetProductForRandomService getProductForRandomService;
    private final GetProductByOptionService getProductByOptionService;
    private final GetProductExplainImageService getProductExplainImageService;
    private final GetByPeriodOption getByPeriodOption;

    private final GetByColorCodeService getByColorCodeService;
    private final GetGraphicOptionService getGraphicOptionService;
    private final GetProductDetailsByOptionService getProductDetailsByOptionService;
    private final ProductDetailsInfoService productDetailsInfoService;
    private final UpdateStockService updateStockService;

    @Transactional(readOnly = true)
    public List<String> getProductExplainImage(Integer productId){
        return getProductExplainImageService.getProductExplainImage(productId);
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse.MainFilterProductData getProductByOption(
            ProductDetailsRequest.ProductOptionForRequest productByOption,Integer memberId){
        return getProductByOptionService.GetProductByOption(productByOption,memberId);
    }



    @Transactional(readOnly = true)
    public ProductDetailsResponse.ProductDetailsForResponse
    getProductDetails(Integer selectedProductId, Integer memberId){
        return getMainProductDetailsService.GetProductDetails(selectedProductId,memberId);
    }

    @Transactional(readOnly = true)
    public List<ProductDetailsResponse.MainProductForResponse> getProductForRandom(
            Integer selectedProductId, Integer memberId
    ){
        return getProductForRandomService.getProductForRandom(selectedProductId,memberId);
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse.ByPeriodOptionList getByPeriodOption(Integer productId,Integer period){
        return getByPeriodOption.getByPeriodOption(productId,period);
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse.ByColorCodeOption getByColorCodeOption(Integer productId,Integer period,String colorCode){
        return getByColorCodeService.getByColorCodeOption(productId,period, colorCode);
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse.DegreeAndStockResponse getGraphicOption(Integer productId,Integer period, String colorCode, Float graphic){
        return getGraphicOptionService.GetGraphicOption(productId,period,colorCode,graphic);
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse.ProductDetailsByOptionResponse getProductDetailsByOption(
            ProductDetailsRequest.ProductDetailsForRequest request, Integer memberId
    ){
        return getProductDetailsByOptionService.GetProductDetailsByOption(request,memberId);
    }

    @Transactional(readOnly = true)
    public ProductResponse.ProductDetailsInfoResponse searchDetailsById(Integer productId){
        return productDetailsInfoService.searchDetailsById(productId);
    }

    @Transactional
    public String updateStock(ProductDetailsRequest.UpdateStockRequest updateStockRequest){
        return updateStockService.updateStock(updateStockRequest);
    }

}
