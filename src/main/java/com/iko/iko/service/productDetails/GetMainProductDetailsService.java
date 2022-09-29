package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMainProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductDetailsForResponse> GetProductDetails(Integer selectedProductId) {
        List<ProductDetailsResponse.ProductDetails> productDetailsForResponseList
                = productDetailsRepository.getProductDetails(selectedProductId);
        List<String> imageUrlOneList = new ArrayList<>();
        List<String> imageUrlTwoList = new ArrayList<>();
        List<String> colorCodeList = new ArrayList<>();
        List<String> degreeList = new ArrayList<>();
        List<String> graphicDiameterList = new ArrayList<>();

        List<ProductDetailsResponse.ProductDetailsForResponse> result = new ArrayList<>();
        HashMap<String, List<String>> resultColorCode = new HashMap<>();
        resultColorCode.put(
                productDetailsForResponseList.get(0).getProductId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getProductDetailsId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getName() + "-__-" +
                        productDetailsForResponseList.get(0).getSeries() + "-__-" +
                        productDetailsForResponseList.get(0).getDiameter() + "-__-" +
                        productDetailsForResponseList.get(0).getPrice() + "-__-" +
                        productDetailsForResponseList.get(0).getDiscount(),
                colorCodeList
        );
        HashMap<String, List<String>> resultImageUrlOneList = new HashMap<>();
        if (productDetailsForResponseList.get(0).getImageType() == 1)
            resultImageUrlOneList.put(
                    productDetailsForResponseList.get(0).getProductId().toString() + "-__-" +
                            productDetailsForResponseList.get(0).getProductDetailsId().toString() + "-__-" +
                            productDetailsForResponseList.get(0).getName() + "-__-" +
                            productDetailsForResponseList.get(0).getSeries() + "-__-" +
                            productDetailsForResponseList.get(0).getDiameter() + "-__-" +
                            productDetailsForResponseList.get(0).getPrice() + "-__-" +
                            productDetailsForResponseList.get(0).getDiscount(),
                    imageUrlOneList
            );

        HashMap<String, List<String>> resultImageUrlTwoList = new HashMap<>();
        if (productDetailsForResponseList.get(0).getImageType() == 2)
            resultImageUrlTwoList.put(
                    productDetailsForResponseList.get(0).getProductId().toString() + "-__-" +
                            productDetailsForResponseList.get(0).getProductDetailsId().toString() + "-__-" +
                            productDetailsForResponseList.get(0).getName() + "-__-" +
                            productDetailsForResponseList.get(0).getSeries() + "-__-" +
                            productDetailsForResponseList.get(0).getDiameter() + "-__-" +
                            productDetailsForResponseList.get(0).getPrice() + "-__-" +
                            productDetailsForResponseList.get(0).getDiscount(),
                    imageUrlTwoList
            );
        HashMap<String, List<String>> resultDegreeList = new HashMap<>();
        resultDegreeList.put(
                productDetailsForResponseList.get(0).getProductId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getProductDetailsId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getName() + "-__-" +
                        productDetailsForResponseList.get(0).getSeries() + "-__-" +
                        productDetailsForResponseList.get(0).getDiameter().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getPrice().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getDiscount().toString(),
                degreeList
        );
        HashMap<String, List<String>> resultGraphicDiameterList = new HashMap<>();
        resultGraphicDiameterList.put(
                productDetailsForResponseList.get(0).getProductId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getProductDetailsId().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getName() + "-__-" +
                        productDetailsForResponseList.get(0).getSeries() + "-__-" +
                        productDetailsForResponseList.get(0).getDiameter().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getPrice().toString() + "-__-" +
                        productDetailsForResponseList.get(0).getDiscount().toString(),
                graphicDiameterList
        );

        for (ProductDetailsResponse.ProductDetails productDetails : productDetailsForResponseList) {
            List<String> colorCodeForMap = new ArrayList<>();
            colorCodeForMap.add(productDetails.getColorCode());
            resultColorCode.put(
                    productDetails.getProductId().toString() + "-__-" +
                            productDetails.getProductDetailsId().toString() + "-__-" +
                            productDetails.getName() + "-__-" +
                            productDetails.getSeries() + "-__-" +
                            productDetails.getDiameter().toString() + "-__-" +
                            productDetails.getPrice().toString() + "-__-" +
                            productDetails.getDiscount().toString(),
                    colorCodeForMap
            );
        }
        for (ProductDetailsResponse.ProductDetails productDetails : productDetailsForResponseList) {
            if (productDetails.getImageType() == 1) {
                List<String> imageUrlOneForMap = new ArrayList<>();
                imageUrlOneForMap.add(productDetails.getImageUrl());
                resultImageUrlOneList.put(
                        productDetails.getProductId().toString() + "-__-" +
                                productDetails.getProductDetailsId().toString() + "-__-" +
                                productDetails.getName() + "-__-" +
                                productDetails.getSeries() + "-__-" +
                                productDetails.getDiameter().toString() + "-__-" +
                                productDetails.getPrice().toString() + "-__-" +
                                productDetails.getDiscount().toString(),
                        imageUrlOneForMap
                );
            }
            if (productDetails.getImageType() == 2) {
                List<String> imageUrlTwoForMap = new ArrayList<>();
                imageUrlTwoForMap.add(productDetails.getImageUrl());
                resultImageUrlTwoList.put(
                        productDetails.getProductId().toString() + "-__-" +
                                productDetails.getProductDetailsId().toString() + "-__-" +
                                productDetails.getName() + "-__-" +
                                productDetails.getSeries() + "-__-" +
                                productDetails.getDiameter().toString() + "-__-" +
                                productDetails.getPrice().toString() + "-__-" +
                                productDetails.getDiscount().toString(),
                        imageUrlTwoForMap
                );
            }
        }
        for (ProductDetailsResponse.ProductDetails productDetails : productDetailsForResponseList) {
            List<String> degreeForMap = new ArrayList<>();
            degreeForMap.add(productDetails.getDegree().toString());
            resultDegreeList.put(
                    productDetails.getProductId().toString() + "-__-" +
                            productDetails.getProductDetailsId().toString() + "-__-" +
                            productDetails.getName() + "-__-" +
                            productDetails.getSeries() + "-__-" +
                            productDetails.getDiameter().toString() + "-__-" +
                            productDetails.getPrice().toString() + "-__-" +
                            productDetails.getDiscount().toString(),
                    degreeForMap
            );
        }
        for (ProductDetailsResponse.ProductDetails productDetails : productDetailsForResponseList) {
            List<String> graphicDiameterForMap = new ArrayList<>();
            graphicDiameterForMap.add(productDetails.getColorCode());
            resultGraphicDiameterList.put(
                    productDetails.getProductId().toString() + "-__-" +
                            productDetails.getProductDetailsId().toString() + "-__-" +
                            productDetails.getName() + "-__-" +
                            productDetails.getSeries() + "-__-" +
                            productDetails.getDiameter().toString() + "-__-" +
                            productDetails.getPrice().toString() + "-__-" +
                            productDetails.getDiscount().toString(),
                    graphicDiameterForMap
            );
        }
        for (String keyS : resultColorCode.keySet()) {
            String[] dataList = keyS.split("-__-");
            ProductDetailsResponse.ProductDetailsForResponse checkData = new ProductDetailsResponse.ProductDetailsForResponse(
                    Integer.parseInt(dataList[0]),
                    Integer.parseInt(dataList[1]),
                    dataList[2],
                    dataList[3],
                    Float.parseFloat(dataList[4]),
                    resultColorCode.get(keyS),
                    Integer.parseInt(dataList[6]),
                    Integer.parseInt(dataList[7]),
                    resultImageUrlOneList.get(keyS),
                    resultImageUrlTwoList.get(keyS),
                    resultDegreeList.get(keyS),
                    resultGraphicDiameterList.get(keyS)
            );
            result.add(checkData);
        }
        return result;
    }
}
