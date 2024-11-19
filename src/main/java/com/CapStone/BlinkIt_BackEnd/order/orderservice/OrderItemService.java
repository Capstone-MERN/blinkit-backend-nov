package com.CapStone.BlinkIt_BackEnd.order.orderservice;

import com.CapStone.BlinkIt_BackEnd.order.orderrepo.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

}
