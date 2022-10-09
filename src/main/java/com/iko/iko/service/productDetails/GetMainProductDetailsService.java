package com.iko.iko.service.productDetails;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.LongToIntFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMainProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public ProductDetailsResponse.ProductDetailsForResponse GetProductDetails(Integer selectedProductId,Integer memberId) {
        Integer isFavorite=0;

        List<Integer> productDetailsIdList = productRepository.getAllProductDetailsIdByProductId(selectedProductId);

        HashSet<Integer> periodSet=new HashSet<>();
        HashSet<String> colorCodeSet=new HashSet<>();
        HashSet<Float> graphicDiameterSet=new HashSet<>();

        if(memberId!=0) {
            Member member = validateLoginStatus();
            isFavorite = (int)(long)productRepository.getMemberIsFavorite(memberId, selectedProductId);
        }

            List<ProductDetailsResponse.ProductDetails> productDetailsData
                    =productDetailsRepository.getProductDetails(selectedProductId);
            for(ProductDetailsResponse.ProductDetails detailsData : productDetailsData) {
                periodSet.add(detailsData.getPeriod());
                colorCodeSet.add(detailsData.getColorCode());
                graphicDiameterSet.add(detailsData.getGraphicDiameter());
            }

        List<ProductResponse.GetAllProductDistinct> productData
                =productRepository.getAllProductByProductId(selectedProductId);

        List<ProductDetailsResponse.GetColorCodeAndImageUrl> k
                = productDetailsRepository.getColorAndImage(selectedProductId);


        List<ProductDetailsResponse.typeAndImage> imageByProductId
                =productDetailsRepository.getTypeAndImageByProductId(selectedProductId);

        String mainImage = new String();
        List<String> subImageList=new ArrayList<>();
        for(ProductDetailsResponse.typeAndImage imageList:imageByProductId){
            if(imageList.getImageType().equals(1)){
                mainImage=imageList.getImageUrl();
            }
            else if(imageList.getImageType().equals(2)){
                subImageList.add(imageList.getImageUrl());
            }
            else continue;
        }
        List<Integer> periodList =new ArrayList<>(periodSet);
        List<String> colorCodeList =new ArrayList<>(colorCodeSet);
        List<Float> graphicDiameterList =new ArrayList<>(graphicDiameterSet);


        ProductDetailsResponse.ProductDetailsForResponse result
                =new ProductDetailsResponse.ProductDetailsForResponse(
                        isFavorite,
                selectedProductId,
                productData.get(0).getName(),
                        productData.get(0).getSeries(),
                productData.get(0).getPrice(),
                productData.get(0).getDiscount(),
                mainImage,
                subImageList,
                periodList,
                colorCodeList,
                graphicDiameterList
                );
        return result;

    }
    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
