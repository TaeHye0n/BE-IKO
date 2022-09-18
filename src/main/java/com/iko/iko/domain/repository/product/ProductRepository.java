package com.iko.iko.domain.repository.product;

import com.iko.iko.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer >{

    @Select("SELECT * FROM tm_product ORDER BY p_code DESC")
    public List<Products> selectAllProducts();

}
