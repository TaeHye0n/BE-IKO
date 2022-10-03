package com.iko.iko.service.product;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.product.dto.request.ProductRequest.ProductSaveRequest;
import com.iko.iko.controller.product.dto.request.ProductRequest.ProductSaveRequest.ProductOptionSaveRequest;
import com.iko.iko.domain.entity.Image;
import com.iko.iko.domain.entity.LinkProductDetailsImage;
import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.entity.ProductDetails;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.linkProductDetailsImage.LinkProductDetailsImageRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveProductService {
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final ImageRepository imageRepository;
    private final LinkProductDetailsImageRepository linkProductDetailsImageRepository;

    @Transactional
    public String saveProduct(ProductSaveRequest productSaveRequest) {
        Product product = productRepository.save(productSaveRequest.toEntity());
        if (product.getProductId() == null) {
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }

        for(ProductOptionSaveRequest productOptionSaveRequest : productSaveRequest.getProductOptionSaveRequestList()){
            for(Float degree : productOptionSaveRequest.getDegree()){
                ProductDetails productDetails = productOptionSaveRequest.toEntity();
                productDetails.setProductIdFk(product.getProductId());
                productDetails.setDegree(degree);
                ProductDetails newProductDetails = productDetailsRepository.save(productDetails);
                if(newProductDetails.getProductDetailsId() == null){
                    throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                }

                int count = 0;
                for(String imageUrl : productOptionSaveRequest.getImageUrl()){
                    Image image = new Image();
                    image.setImageUrl(imageUrl);
                    if(count == 0){
                        image.setImageType(1);
                    }
                    else{
                        image.setImageType(2);
                    }
                    Image newImage = imageRepository.save(image);
                    if(newImage.getImageId() == null){
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                    count++;
                    LinkProductDetailsImage linkProductDetailsImage = new LinkProductDetailsImage();
                    linkProductDetailsImage.setProductDetailsId(newProductDetails.getProductDetailsId());
                    linkProductDetailsImage.setImageId(newImage.getImageId());
                    LinkProductDetailsImage newLinkProductDetailsImage = linkProductDetailsImageRepository.save(linkProductDetailsImage);
                    if(newLinkProductDetailsImage.getProductDetailsId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                }

                for(String explanationImageUrl : productOptionSaveRequest.getExplanationImageUrl()){
                    Image image = new Image();
                    image.setImageUrl(explanationImageUrl);
                    image.setImageType(3);
                    Image newImage = imageRepository.save(image);
                    if(newImage.getImageId() == null){
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                    LinkProductDetailsImage linkProductDetailsImage = new LinkProductDetailsImage();
                    linkProductDetailsImage.setProductDetailsId(newProductDetails.getProductDetailsId());
                    linkProductDetailsImage.setImageId(newImage.getImageId());
                    LinkProductDetailsImage newLinkProductDetailsImage = linkProductDetailsImageRepository.save(linkProductDetailsImage);
                    if(newLinkProductDetailsImage.getProductDetailsId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                }
            }
        }

        return "OK";
    }
}
