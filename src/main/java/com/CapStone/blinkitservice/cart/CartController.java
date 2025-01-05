package com.CapStone.blinkitservice.cart;

import com.CapStone.blinkitservice.cart.model.UpdateCartRequest;
import com.CapStone.blinkitservice.cart.model.CartResponse;
import com.CapStone.blinkitservice.common.error.GenericErrorResponse;
import com.CapStone.blinkitservice.common.error.exception.InvalidCartPayloadResponse;
import com.CapStone.blinkitservice.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updateCart(@RequestBody UpdateCartRequest updateCartRequest, @AuthenticationPrincipal String email){
        try{
             CartResponse response = cartService.updateCart(updateCartRequest, email);
             return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvalidCartPayloadResponse e){
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get")
    public ResponseEntity<GenericResponse> getCart(@AuthenticationPrincipal String email){
        try{
            CartResponse response = cartService.getCart(email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new GenericErrorResponse<>(e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
