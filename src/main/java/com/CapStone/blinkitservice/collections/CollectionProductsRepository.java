package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.collections.entity.CollectionProducts;
import com.CapStone.blinkitservice.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionProductsRepository extends JpaRepository<CollectionProducts, Integer> {

    @Query(value = "SELECT p.* FROM products p INNER JOIN collection_products cp ON p.id = cp.product_id WHERE cp.collection_id = :collectionId", nativeQuery = true)
    public List<ProductEntity> findProductsByCollectionId(@Param("collectionId") int collectionId);

}
