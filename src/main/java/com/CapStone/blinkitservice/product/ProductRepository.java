package com.CapStone.blinkitservice.product;

import com.CapStone.blinkitservice.product.entity.ProductEntity;
import com.CapStone.blinkitservice.product.model.ProductMaxOrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("""
    SELECT p
    FROM ProductEntity p
    LEFT JOIN p.brandEntity b
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(p.keyFeatures) LIKE LOWER(CONCAT('%', :query, '%'))
    ORDER BY
        CASE
            WHEN LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) THEN 1
            WHEN LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) THEN 2
            WHEN LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) THEN 3
            WHEN LOWER(p.keyFeatures) LIKE LOWER(CONCAT('%', :query, '%')) THEN 4
            ELSE 5
        END
    """)
    Page<ProductEntity> findAllProductsByQuery(@Param("query") String query, Pageable pageable);


    @Query(value = "select p.id,p.max_order_limit from products p where p.id in(:productIds)",nativeQuery = true)
    List<ProductMaxOrderProjection> findMaxOrderLimitByProductIds(@Param("productIds") List<Integer> productIds);

    Page<ProductEntity> findBySubCategoryEntityId(Integer subCategoryId, Pageable pageable);

}
