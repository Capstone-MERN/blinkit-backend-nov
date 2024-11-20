package com.CapStone.blinkitservice.brand.BrandRepository;

import com.CapStone.blinkitservice.brand.BrandEntity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity , Integer> {
}
