package com.CapStone.blinkitservice.product;

import com.CapStone.blinkitservice.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
