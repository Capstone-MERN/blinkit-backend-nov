package com.CapStone.blinkitservice.order;

import com.CapStone.blinkitservice.order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Integer> {
}
