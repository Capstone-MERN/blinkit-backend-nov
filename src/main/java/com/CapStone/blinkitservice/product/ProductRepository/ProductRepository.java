package com.CapStone.blinkitservice.product.ProductRepository;

import com.CapStone.blinkitservice.product.ProductEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
