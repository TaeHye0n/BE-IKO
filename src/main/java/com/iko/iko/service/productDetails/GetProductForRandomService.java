package com.iko.iko.service.productDetails;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Product;
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
public class GetProductForRandomService {

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<ProductDetailsResponse.MainProductForResponse> getProductForRandom(
            Integer selectedProductId, Integer memberId
    ){
        Integer isFavorite=0;

        Integer totalCount=4;
        List<ProductDetailsResponse.MainProductForResponse> result = new ArrayList<>();

        List<Integer> productIdList=productRepository.getAllProductId();
        for(int i=0;i<productIdList.size();i++){
            if(productIdList.get(i).equals(selectedProductId)){
                productIdList.remove(i);
                break;
            }
        }
        for(int i=0;i<4;i++){
            int size=productIdList.size();

            Random rand=new Random();
            int index=rand.nextInt(size);
            Integer productId= productIdList.get(index);
            productIdList.remove(index);

            if(!memberId.equals(0)){
                Member member =validateLoginStatus();
                isFavorite = (int)(long)productRepository.getMemberIsFavorite(memberId,productId);
            }

            Product mainProduct
                    =productRepository.getProductDistinctByProductId(productId);

            List<ProductDetailsResponse.GetGraphicDiameter> graphicList = productDetailsRepository.getGraphic(productId);
            List<Float> gList = new ArrayList<>();
            List<ProductDetailsResponse.GetColorCodeAndImageUrl> iList = new ArrayList<>();

            List<ProductDetailsResponse.GetColorCodeAndImageUrl> k
                    = productDetailsRepository.getColorAndImage(productId);
            for(ProductDetailsResponse.GetColorCodeAndImageUrl ttpp: k){
                iList.add(ttpp);
            }

            //
            for(ProductDetailsResponse.GetGraphicDiameter tp : graphicList){
                gList.add(tp.getGraphicDiameter());
            }
            ProductDetailsResponse.MainProductForResponse checkData
                    =new ProductDetailsResponse.MainProductForResponse(
                            totalCount,
                    isFavorite,
                    productId,
                    mainProduct.getSeries(),
                    gList,
                    mainProduct.getPrice(),
                    mainProduct.getDiscount(),
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
