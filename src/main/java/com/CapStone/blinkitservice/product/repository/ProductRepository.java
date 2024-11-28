package com.CapStone.blinkitservice.product.repository;

import com.CapStone.blinkitservice.product.dto.ProductResponseDto;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    @Query(value = """
            SELECT p.* FROM products p
            LEFT JOIN brands b ON p.brand_id = b.id
            WHERE p.name LIKE %:query%
               OR b.title LIKE %:query%
               OR p.description LIKE %:query%
               OR p.key_features LIKE %:query%
            ORDER BY
                CASE
                    WHEN p.name LIKE %:query% THEN 1
                    WHEN b.title LIKE %:query% THEN 2
                    WHEN p.description LIKE %:query% THEN 3
                    WHEN p.key_features LIKE %:query% THEN 4
                    ELSE 5
                END
            """,
            countQuery = """
            SELECT COUNT(*) FROM products p
            LEFT JOIN brands b ON p.brand_id = b.id
            WHERE p.name LIKE %:query%
               OR b.title LIKE %:query%
               OR p.description LIKE %:query%
               OR p.key_features LIKE %:query%
            """, nativeQuery = true)
    Page<ProductEntity> findAllProductsByQuery(String query, Pageable pageable);

}
