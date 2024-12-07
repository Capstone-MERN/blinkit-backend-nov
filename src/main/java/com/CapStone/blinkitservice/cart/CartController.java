package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.UpdateCartResponse;
import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.error.exception.InvalidCartPayloadResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
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
    public ResponseEntity<GenericResponse> updateCart(@RequestBody UpdateCartRequest updateCartRequest, @AuthenticationPrincipal String email){

        try{
             UpdateCartResponse response = cartService.updateCart(updateCartRequest, email);
             return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvalidCartPayloadResponse e){
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
