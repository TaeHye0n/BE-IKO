package com.iko.iko.service.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.controller.ProductDetails.dto.ProductDetailsRequest;

import java.util.*;
import java.util.Map.Entry;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class GetProductByOptionService {

    private final ProductDetailsRepository productDetailsRepository;

    public List<ProductDetailsResponse.ProductMainByOptionResponse>
    GetProductByOption (ProductDetailsRequest.ProductOptionForRequest productByOption) {
        Integer isFavorite=0;

        List<Integer> productIdList = productDetailsRepository.getProductIdByMainOption(productByOption);
        Map<Integer, Integer> productIdAndCount=new HashMap<Integer, Integer>();

        //Get ProductId By Option List
        for(Integer idList : productIdList){

            List<Integer> productDetailsIdList =productDetailsRepository.getProductDetailsIdByProductIdForBest(idList);
            productIdAndCount.put(idList,(int)(long)productDetailsIdList.stream().count());

        }
        //Sort 인기순 Key : productId, Value : 주문량
        List<Map.Entry<Integer, Integer>> entryList=new ArrayList<Entry<Integer,Integer>>(productIdAndCount.entrySet());

        Collections.sort(entryList, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2){
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });

        return null;
    }

}
