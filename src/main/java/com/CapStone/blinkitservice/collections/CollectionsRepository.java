package com.CapStone.blinkitservice.collections;

import com.CapStone.blinkitservice.collections.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionsRepository extends JpaRepository<CollectionEntity, Integer> {

    @Query(value = "SELECT * FROM collections WHERE is_active = 1", nativeQuery = true)
    public List<CollectionEntity> getActiveCollections();



}
