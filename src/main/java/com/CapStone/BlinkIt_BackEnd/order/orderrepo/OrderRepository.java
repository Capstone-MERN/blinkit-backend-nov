package com.CapStone.BlinkIt_BackEnd.order.orderrepo;

import com.CapStone.BlinkIt_BackEnd.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {


}
