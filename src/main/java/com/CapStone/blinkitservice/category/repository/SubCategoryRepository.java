package com.CapStone.blinkitservice.category.repository;

import com.CapStone.blinkitservice.category.entity.SubCategoryEntity;
import com.CapStone.blinkitservice.category.model.CategoryVsSubCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Integer> {

    @Query("SELECT sc FROM SubCategoryEntity sc WHERE sc.categoryEntity.id = :categoryId")
    List<SubCategoryEntity> findAllByCategoryId(@Param("categoryId") Integer categoryId);

    @Query(value = """ 
    SELECT
    c.id AS categoryId,
    c.title AS categoryTitle,
    c.image_url AS categoryImageUrl,
    sc.id AS subCategoryId,
    sc.title AS subCategoryTitle
            FROM
    categories c
    LEFT JOIN
    sub_categories sc
    ON
    sc.id = (
    SELECT
    sub.id
            FROM
    sub_categories sub
    WHERE
    sub.category_id = c.id
    ORDER BY
    sub.id ASC
    LIMIT 1
            );""",nativeQuery = true)
    List<CategoryVsSubCategoryProjection> getAllCategoryAndDefaultSubCategory();
}
