package com.iko.iko.domain.repository.product;

import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.controller.product.dto.request.ProductRequest;
import com.iko.iko.domain.entity.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepositoryCustom{
    Page<ProductResponse.GetAllProductDistinct> getAllProduct(Pageable pageable);

    Long getMemberIsFavorite(Integer memberId, Integer selectedProductId);

    List<ProductResponse.GetAllProductDistinct> getAllProductByProductId(Integer productId);

    List<Integer> getAllProductDetailsIdByProductId(Integer productId);

    List<Integer> getAllProductId();

    Product getProductDistinctByProductId(Integer productId);

    List<ProductResponse.productFilterList> getFilterInfo();

    String getProductFeature(Integer productId);

    List<ProductResponse.recommendedProduct> getRecommendedProduct();

    List<ProductResponse.ProductInfo> getProductInfoForAdmin();

    Page<ProductResponse.GetAllProductDistinct> getAllProductByFilter
            (Pageable pageable, Integer productId);
    List<Integer> getProductIdBySearchName(String searchName);
    Long updateProduct(ProductRequest.ProductUpdateRequest productUpdateRequest);
    Integer searchProductIdByNameForAdmin(String productName);
    List<ProductResponse.ProductIdAndCreatedAt> getProductIdByNewest();

    Long deleteProduct(Integer productId);

}
