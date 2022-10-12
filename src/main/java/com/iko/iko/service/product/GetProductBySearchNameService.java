package com.iko.iko.service.product;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GetProductBySearchNameService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public ProductDetailsResponse.MainFilterProductData getProductBySearchName(String searchName,Integer memberId){
        Integer isFavorite=0;

        List<ProductDetailsResponse.MainProductForResponseNotTotalCount> result = new ArrayList<>();

        List<Integer> pList=
        productRepository.getProductIdBySearchName(searchName);

        Map<Integer, Integer> productIdAndCount=new HashMap<Integer, Integer>();

        //Get ProductId By Option List
        for(Integer idList : pList){

            List<Integer> productDetailsIdList =productDetailsRepository.getProductDetailsIdByProductIdForBest(idList);
            productIdAndCount.put(idList,(int)(long)productDetailsIdList.stream().count());

        }
        //Sort 인기순 Key : productId, Value : 주문량
        List<Map.Entry<Integer, Integer>> entryList=new ArrayList<Map.Entry<Integer,Integer>>(productIdAndCount.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2){
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
        Integer totalCount=entryList.size();

        for(Map.Entry<Integer,Integer> eList : entryList){
            Product mainProduct
                    =productRepository.getProductDistinctByProductId(eList.getKey());
            Integer productId=eList.getKey();
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
        ProductDetailsResponse.MainFilterProductData finalResult =new ProductDetailsResponse.MainFilterProductData(
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
