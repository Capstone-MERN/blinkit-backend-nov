package com.CapStone.blinkitservice.order.orderservice;

import com.CapStone.blinkitservice.order.orderrepo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


}
