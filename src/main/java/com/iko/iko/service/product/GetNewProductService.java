package com.iko.iko.service.product;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNewProductService {
    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public ProductDetailsResponse.MainFilterProductData getNewProduct(Integer memberId){
        Integer totalCount=0;

        Calendar mon = Calendar.getInstance();
        mon.add(Calendar.MONTH , -1);
        Date monthAgo=mon.getTime();

        List<ProductDetailsResponse.MainProductForResponseNotTotalCount> result = new ArrayList<>();

        List<ProductResponse.ProductIdAndCreatedAt> productIdAndCreatedAtList= productRepository.getProductIdByNewest();
        for(ProductResponse.ProductIdAndCreatedAt pr : productIdAndCreatedAtList){
            if(pr.getCreatedAt().before(monthAgo)){
                continue;
            }
            else {
                totalCount=totalCount+1;
                Integer isFavorite=0;

                if(!memberId.equals(0)) {
                    Member member = validateLoginStatus();
                    isFavorite = (int)(long)productRepository.getMemberIsFavorite(member.getMemberId(), pr.getProductId());
                }
                Product mainProduct
                        =productRepository.getProductDistinctByProductId(pr.getProductId());
                Integer productId=pr.getProductId();
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
                ProductDetailsResponse.MainProductForResponseNotTotalCount checkData
                        =new ProductDetailsResponse.MainProductForResponseNotTotalCount(
                        isFavorite,
                        productId,
                        mainProduct.getSeries(),
                        gList,
                        mainProduct.getPrice(),
                        mainProduct.getDiscount(),
                        iList);

                result.add(checkData);
            }

        }
        ProductDetailsResponse.MainFilterProductData finalResult= new ProductDetailsResponse.MainFilterProductData(
                totalCount,
                result
        );
        return finalResult;
    }
    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
