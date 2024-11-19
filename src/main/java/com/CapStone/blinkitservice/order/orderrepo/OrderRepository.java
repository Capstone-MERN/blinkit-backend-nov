package com.CapStone.blinkitservice.order.orderrepo;

import com.CapStone.blinkitservice.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {


}
