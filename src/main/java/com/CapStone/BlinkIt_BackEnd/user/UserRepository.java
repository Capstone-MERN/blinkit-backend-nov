package com.CapStone.BlinkIt_BackEnd.user;

import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByMobileNumber(String mobileNumber);
}
