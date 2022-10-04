package com.iko.iko.service.product;

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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductService {
    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    public List<ProductDetailsResponse.MainProductForResponse> GetMainProduct(Pageable pageable) {
        Member member = validateLoginStatus();

        List<ProductDetailsResponse.MainProductForResponse> result = new ArrayList<>();

        List<ProductResponse.GetAllProductDistinct> mainProduct = productRepository.getAllProduct();

        for(ProductResponse.GetAllProductDistinct tmp : mainProduct){
            Long isFavorite;
            if(member.getMemberId()!=0) {
                isFavorite = productRepository.getMemberIsFavorite(member.getMemberId(), tmp.getProductId());
            }
            else{
                isFavorite = Long.valueOf(0);
            }

            List<ProductDetailsResponse.GetGraphicDiameter> graphicList = productDetailsRepository.getGraphic(tmp.getProductId());
            List<Float> gList = new ArrayList<>();
            List<ProductDetailsResponse.GetColorCodeAndImageUrl> iList = new ArrayList<>();
            List<ProductDetailsResponse.MainProduct> mainProductList = productDetailsRepository.getMainProduct(pageable, tmp.getProductId());


                List<ProductDetailsResponse.GetColorCodeAndImageUrl> k
                        = productDetailsRepository.getColorAndImage(tmp.getProductId());
                for(ProductDetailsResponse.GetColorCodeAndImageUrl ttpp: k){
                    iList.add(ttpp);
                }

            //
            for(ProductDetailsResponse.GetGraphicDiameter tp : graphicList){
                gList.add(tp.getGraphicDiameter());
            }
            ProductDetailsResponse.MainProductForResponse checkData
                    =new ProductDetailsResponse.MainProductForResponse(
                            isFavorite,
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

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}