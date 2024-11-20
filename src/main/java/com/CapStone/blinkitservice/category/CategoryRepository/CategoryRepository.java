package com.CapStone.blinkitservice.category.CategoryRepository;

import com.CapStone.blinkitservice.category.CategoryEntity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
}
