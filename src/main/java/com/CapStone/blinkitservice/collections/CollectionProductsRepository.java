package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.collections.entity.CollectionProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionProductsRepository extends JpaRepository<CollectionProducts, Integer> {

    @Query("SELECT cp FROM CollectionProducts cp WHERE cp.collection.isActive = true")
    List<CollectionProducts> findActiveCollectionProducts();

}
