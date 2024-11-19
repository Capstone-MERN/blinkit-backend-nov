package com.CapStone.BlinkIt_BackEnd.cart;

import com.CapStone.BlinkIt_BackEnd.cart.entity.CartItem;
import com.CapStone.BlinkIt_BackEnd.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

}
