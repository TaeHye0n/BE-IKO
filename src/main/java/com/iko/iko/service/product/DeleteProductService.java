package com.iko.iko.service.product;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Product;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeleteProductService {

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final ImageRepository imageRepository;
    private final LinkProductDetailsImageRepository linkProductDetailsImageRepository;
    private final LinkOrderDetailsRepository linkOrderDetailsRepository;
    private final FavorRepository favorRepository;
    private final CartRepository cartRepository;

    @Transactional
    public String deleteProduct(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
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
            productRepository.deleteProduct(productId);
            return "Ok";
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
    }
}
