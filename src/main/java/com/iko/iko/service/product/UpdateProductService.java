package com.iko.iko.service.product;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.product.dto.request.ProductRequest.*;
import com.iko.iko.domain.entity.Image;
import com.iko.iko.domain.entity.LinkProductDetailsImage;
import com.iko.iko.domain.entity.ProductDetails;
import com.iko.iko.domain.repository.cart.CartRepository;
import com.iko.iko.domain.repository.favor.FavorRepository;
import com.iko.iko.domain.repository.image.ImageRepository;
import com.iko.iko.domain.repository.linkOrderDetails.LinkOrderDetailsRepository;
import com.iko.iko.domain.repository.linkProductDetailsImage.LinkProductDetailsImageRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.productDetails.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final ImageRepository imageRepository;
    private final LinkProductDetailsImageRepository linkProductDetailsImageRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;
    private final FavorRepository favorRepository;
    private final CartRepository cartRepository;

    @Transactional
    public String updateProduct(ProductUpdateRequest productUpdateRequest) {
        productRepository.updateProduct(productUpdateRequest);
        Integer productId = productRepository.searchProductIdByNameForAdmin(productUpdateRequest.getProductName());
        List<Integer> productDetailsIdList = productDetailsRepository.searchProductDetailsIdByProductId(productId);
        List<Integer> imageIdList = linkProductDetailsImageRepository.searchImageIdByProductId(productId);
        if (productDetailsIdList.size() == 0 || imageIdList.size() == 0)
            throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        for (Integer imageId : imageIdList) {
            linkProductDetailsImageRepository.deleteLinkProductDetailsImage(imageId);
        }
        for (Integer imageId : imageIdList) {
            imageRepository.deleteImage(imageId);
        }
        for(Integer productDetailsId : productDetailsIdList){
            linkOrderDetailsRepository.deleteLinkOrder(productDetailsId);
            cartRepository.deleteCartForAdmin(productDetailsId);
        }
        favorRepository.deleteFavorForAdmin(productId);
        productDetailsRepository.deleteProductDetails(productId);

        for (ProductUpdateRequest.ProductOptionUpdateRequest productOptionUpdateRequest : productUpdateRequest.getProductOptionUpdateRequestList()) {
            for (Float degree : productOptionUpdateRequest.getDegree()) {
                ProductDetails productDetails = productOptionUpdateRequest.toEntity();
                productDetails.setProductIdFk(productId);
                productDetails.setDegree(degree);
                ProductDetails newProductDetails = productDetailsRepository.save(productDetails);
                if (newProductDetails.getProductDetailsId() == null) {
                    throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                }

                int count = 0;
                for (String imageUrl : productOptionUpdateRequest.getImageUrl()) {
                    Image image = new Image();
                    image.setImageUrl(imageUrl);
                    if (count == 0) {
                        image.setImageType(1);
                    } else {
                        image.setImageType(2);
                    }
                    Image newImage = imageRepository.save(image);
                    if (newImage.getImageId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                    count++;
                    LinkProductDetailsImage linkProductDetailsImage = new LinkProductDetailsImage();
                    linkProductDetailsImage.setProductDetailsId(newProductDetails.getProductDetailsId());
                    linkProductDetailsImage.setImageId(newImage.getImageId());
                    LinkProductDetailsImage newLinkProductDetailsImage = linkProductDetailsImageRepository.save(linkProductDetailsImage);
                    if (newLinkProductDetailsImage.getProductDetailsId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                }

                for (String explanationImageUrl : productOptionUpdateRequest.getExplanationImageUrl()) {
                    Image image = new Image();
                    image.setImageUrl(explanationImageUrl);
                    image.setImageType(3);
                    Image newImage = imageRepository.save(image);
                    if (newImage.getImageId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                    LinkProductDetailsImage linkProductDetailsImage = new LinkProductDetailsImage();
                    linkProductDetailsImage.setProductDetailsId(newProductDetails.getProductDetailsId());
                    linkProductDetailsImage.setImageId(newImage.getImageId());
                    LinkProductDetailsImage newLinkProductDetailsImage = linkProductDetailsImageRepository.save(linkProductDetailsImage);
                    if (newLinkProductDetailsImage.getProductDetailsId() == null) {
                        throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
                    }
                }
            }
        }

        return "OK";
    }

}

