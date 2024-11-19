package com.CapStone.BlinkIt_BackEnd.address;

import com.CapStone.BlinkIt_BackEnd.address.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {


}
