package com.CapStone.blinkitservice.order;

import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
import com.CapStone.blinkitservice.order.model.OrderRequest;
import com.CapStone.blinkitservice.order.model.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @PostMapping("/place-order")
   public ResponseEntity<GenericResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest, @AuthenticationPrincipal String email){

       OrderResponse response= orderService.placeOrder(orderRequest,email);
       return ResponseEntity.ok(response);

    }
}
