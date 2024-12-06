package com.CapStone.blinkitservice.user.Repository;

import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressBookEntity, Integer> {
}
