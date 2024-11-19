package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.auth.UserAuthResponse;
import com.CapStone.blinkitservice.cart.model.CartDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @GetMapping("get-items")
    public ResponseEntity<CartDashboard> getMyCart(@AuthenticationPrincipal UserAuthResponse userAuthResponse){

        CartDashboard cartDashboard = cartItemService.getMyCart(userAuthResponse.getEmail());
        return new ResponseEntity<>(cartDashboard, HttpStatus.OK);

    }


}
