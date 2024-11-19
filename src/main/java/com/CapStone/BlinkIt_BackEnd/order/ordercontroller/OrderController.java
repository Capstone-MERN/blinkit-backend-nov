package com.CapStone.BlinkIt_BackEnd.order.ordercontroller;

import com.CapStone.BlinkIt_BackEnd.order.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

}
