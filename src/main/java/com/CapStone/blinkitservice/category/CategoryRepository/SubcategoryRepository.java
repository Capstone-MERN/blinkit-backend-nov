package com.CapStone.blinkitservice.category.CategoryRepository;

import com.CapStone.blinkitservice.category.CategoryEntity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategoryEntity, Integer> {
    @Query(value = "SELECT sc FROM SubCategoryEntity sc WHERE sc.category.id = :categoryId ORDER BY sc.id ASC", nativeQuery = true)
//    @Query(value = "SELECT sc FROM SubCategoryEntity sc WHERE sc.category.id = :categoryId ORDER BY sc.id ASC", nativeQuery = true)
    SubCategoryEntity findFirstSubCategoryByCategoryId(@Param("categoryId") Integer categoryId);
}
