package com.CapStone.BlinkIt_BackEnd.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

}
