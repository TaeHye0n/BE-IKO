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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFilterListService {

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductRepository productRepository;

    public ProductResponse.productFilter GetFilterList() {

        HashSet<String> seriesSet=new HashSet<>();
        HashSet<String> featureSet=new HashSet<>();
        HashSet<Integer> periodSet=new HashSet<>();
        HashSet<Float> graphicSet=new HashSet<>();
        HashSet<String> colorCodeSet=new HashSet<>();

        List<ProductResponse.productFilterList> productFilterLists =
                productRepository.getFilterInfo();
        for (ProductResponse.productFilterList k : productFilterLists) {
            seriesSet.add(k.getSeries());
            featureSet.add(k.getFeature());
        }

        List<ProductDetailsResponse.ProductDetailsFilterList> detailsFilterLists=
                productDetailsRepository.getDetailsFilterInfo();
        for(ProductDetailsResponse.ProductDetailsFilterList k: detailsFilterLists){
            periodSet.add(k.getPeriod());
            graphicSet.add(k.getGraphicDiameter());
            colorCodeSet.add(k.getColorCode());
        }
        List<String> seriesList=new ArrayList<>(seriesSet);
        List<String> featureList=new ArrayList<>(featureSet);
        List<Integer> periodList=new ArrayList<>(periodSet);
        List<Float> graphicList =new ArrayList<>(graphicSet);
        List<String> colorCodeList=new ArrayList<>(colorCodeSet);

        ProductResponse.productFilter checkData
                =new ProductResponse.productFilter(
                seriesList,
                featureList,
                periodList,
                graphicList,
                colorCodeList
        );
        return checkData;
    }


}
