package com.CapStone.blinkitservice.user.repository;

import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntity,Integer> {

    public AddressBookEntity findByUserEntity(UserEntity userEntity);
}
