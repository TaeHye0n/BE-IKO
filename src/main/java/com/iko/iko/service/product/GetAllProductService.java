package com.iko.iko.service.product;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductService {
    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    public List<ProductDetailsResponse.MainProductForResponse> GetMainProduct(Pageable pageable) {
        List<ProductDetailsResponse.MainProductForResponse> result = new ArrayList<>();

        List<ProductResponse.GetAllProductDistinct> mainProduct = productRepository.getAllProduct();

        for(ProductResponse.GetAllProductDistinct tmp : mainProduct){
            List<ProductDetailsResponse.GetGraphicDiameter> graphicList = productDetailsRepository.getGraphic(tmp.getProductId());
            List<Float> gList = new ArrayList<>();
            List<ProductDetailsResponse.GetColorCodeAndImageUrl> iList = new ArrayList<>();
            List<ProductDetailsResponse.MainProduct> mainProductList = productDetailsRepository.getMainProduct(pageable, tmp.getProductId());

            for(ProductDetailsResponse.MainProduct ttp : mainProductList){
                List<ProductDetailsResponse.GetColorCodeAndImageUrl> k
                        = productDetailsRepository.getColorAndImage(ttp.getProductDetailsId(),tmp.getProductId());
                for(ProductDetailsResponse.GetColorCodeAndImageUrl ttpp: k){
                    iList.add(ttpp);
                }
            }
            //
            for(ProductDetailsResponse.GetGraphicDiameter tp : graphicList){
                gList.add(tp.getGraphicDiameter());
            }
            ProductDetailsResponse.MainProductForResponse checkData
                    =new ProductDetailsResponse.MainProductForResponse(
                    tmp.getProductId(),
                    tmp.getSeries(),
                    gList,
                    tmp.getPrice(),
                    tmp.getDiscount(),
                    iList);

            result.add(checkData);
        }
        return result;
    }
}