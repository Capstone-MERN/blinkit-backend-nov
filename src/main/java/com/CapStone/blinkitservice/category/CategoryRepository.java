package com.CapStone.blinkitservice.category;

import com.CapStone.blinkitservice.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
}
