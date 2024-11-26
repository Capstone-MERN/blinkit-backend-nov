package com.CapStone.blinkitservice.category.CategoryRepository;

import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
//    @Query(value = "SELECT c.* FROM categories c " +
//            "LEFT JOIN sub_category_entity s ON c.id = s.category_id",
//            nativeQuery = true)
//    List<CategoryEntity> findAllWithSubcategories();
}
