package com.CapStone.blinkitservice.product;

import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.model.ProductMaxOrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "select p.id,p.max_order_limit from products p where p.id in(:productIds)",nativeQuery = true)
    List<ProductMaxOrderProjection> findMaxOrderLimitByProductIds(@Param("productIds") List<Integer> productIds);
}
