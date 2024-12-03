package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.auth.model.JwtAuthResponse;
import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/update")
    public ResponseEntity<UpdateCartResponse> updateCart(@RequestBody UpdateCartRequest updateCartRequest, @AuthenticationPrincipal String email){

        try{
             UpdateCartResponse response = cartService.updateCartDemo(updateCartRequest, email);
             return new ResponseEntity<UpdateCartResponse>(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<UpdateCartResponse>(new UpdateCartResponse(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
