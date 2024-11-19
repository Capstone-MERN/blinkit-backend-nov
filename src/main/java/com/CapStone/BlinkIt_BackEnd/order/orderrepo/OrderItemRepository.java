package com.CapStone.BlinkIt_BackEnd.order.orderrepo;

import com.CapStone.BlinkIt_BackEnd.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
