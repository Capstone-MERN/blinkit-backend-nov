package com.CapStone.blinkitservice.product.repository;

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
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(p.key_features) LIKE LOWER(CONCAT('%', :query, '%'))
            ORDER BY
                CASE
                    WHEN LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) THEN 1
                    WHEN LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) THEN 2
                    WHEN LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) THEN 3
                    WHEN LOWER(p.key_features) LIKE LOWER(CONCAT('%', :query, '%')) THEN 4
                    ELSE 5
                END
            """,
            countQuery = """
            SELECT COUNT(*) FROM products p
            LEFT JOIN brands b ON p.brand_id = b.id
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(p.key_features) LIKE LOWER(CONCAT('%', :query, '%'))
            """, nativeQuery = true)
    Page<ProductEntity> findAllProductsByQuery(String query, Pageable pageable);



    @Query(value = """
    SELECT p.* FROM products p
    WHERE p.sub_category_id = :subCategoryId
    ORDER BY
        CASE WHEN :filter = 'RELEVANCE' THEN p.name END ASC,
        CASE WHEN :filter = 'PRICE_HIGH_TO_LOW' THEN p.price END DESC,
        CASE WHEN :filter = 'PRICE_LOW_TO_HIGH' THEN p.price END ASC,
        CASE WHEN :filter = 'DISCOUNT' THEN p.discount END DESC,
        CASE WHEN :filter = 'A_TO_Z' THEN p.name END ASC
    """, countQuery = """
    SELECT COUNT(*) FROM products p
    WHERE p.sub_category_id = :subCategoryId
    """, nativeQuery = true)
    Page<ProductEntity> findAllProductsByFilter(Integer subCategoryId, String filter, Pageable pageable);
}
