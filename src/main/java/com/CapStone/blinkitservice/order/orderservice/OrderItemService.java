package com.CapStone.blinkitservice.order.orderservice;

import com.CapStone.blinkitservice.order.orderrepo.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

}
