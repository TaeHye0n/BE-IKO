package com.iko.iko.service.product;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllProductByOptionService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<ProductDetailsResponse.MainProductForResponse> GetMainProductByOption(
            ProductDetailsRequest.ProductOptionForRequest productOption,
            Pageable pageable, Integer memberId){
        Integer isFavorite=0;

        List<ProductDetailsResponse.MainProductForResponse> result = new ArrayList<>();
        List<Integer> SelectedProductIdList=productDetailsRepository.getProductByProductOption(productOption);
        SelectedProductIdList.stream().distinct();

        Integer totalCount=SelectedProductIdList.size();



        for(Integer productIdList : SelectedProductIdList){
            List<ProductResponse.GetAllProductDistinct> mainProduct
                    =productRepository.getAllProductByProductId(productIdList);

            for(ProductResponse.GetAllProductDistinct tmp : mainProduct){
                if(!memberId.equals(0)) {
                    Member member = validateLoginStatus();
                    isFavorite = (int)(long)productRepository.getMemberIsFavorite(member.getMemberId(), tmp.getProductId());
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
                                totalCount,
                        isFavorite,
                        tmp.getProductId(),
                        tmp.getSeries(),
                        gList,
                        tmp.getPrice(),
                        tmp.getDiscount(),
                        iList);

                result.add(checkData);
            }

        }
        return result;
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
