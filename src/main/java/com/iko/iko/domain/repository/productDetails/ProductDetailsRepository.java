package com.iko.iko.domain.repository.productDetails;


import com.iko.iko.domain.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer>, ProductDetailsRepositoryCustom{

}
