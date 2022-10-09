package com.iko.iko.service.productDetails;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.image.ImageRepositoryImpl;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetProductDetailsByOptionService {

    private final ProductDetailsRepository productDetailsRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ProductDetailsResponse.ProductDetailsByOptionResponse GetProductDetailsByOption(
            ProductDetailsRequest.ProductDetailsForRequest request, Integer memberId
    ){
        Integer isFavorite=0;

        Integer productDetailsId =productDetailsRepository.getProductDetailsIdByOption(request);

        ProductDetailsResponse.ProductDetailsByOption subResult
                =productDetailsRepository.getProductDetailsByProductDetailsId(productDetailsId);

        List<ProductDetailsResponse.typeAndImage> images=
                imageRepository.getTypeAndImageByProductDetailsId(productDetailsId);


        if(!memberId.equals(0)) {
            Member member = validateLoginStatus();
            isFavorite = (int)(long)productRepository.getMemberIsFavorite(memberId, request.getProductId());
        }

        ProductDetailsResponse.ProductDetailsByOptionResponse result
                =new ProductDetailsResponse.ProductDetailsByOptionResponse(
                        isFavorite,
                productDetailsId,
                subResult.getProductName(),
                subResult.getColor(),
                subResult.getDiscount(),
                subResult.getDetailsPrice(),
                images

        );

        return result;
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
