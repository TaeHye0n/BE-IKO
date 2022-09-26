package com.iko.iko.domain.repository.productDetails;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.ProductDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>{
    List<ProductDetailsResponse.ProductDetailsForResponse> getMainProduct(Pageable pageable);

    //List<ProductDetails> findByProductId(int productId);

}
