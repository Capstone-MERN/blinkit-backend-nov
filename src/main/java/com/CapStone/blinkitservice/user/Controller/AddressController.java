package com.CapStone.blinkitservice.user.Controller;

import com.CapStone.blinkitservice.user.Service.AddressService;
import com.CapStone.blinkitservice.user.model.AddressRequest;
import com.CapStone.blinkitservice.user.model.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/update-address")
    public ResponseEntity<AddressResponse> addUserAddress(@AuthenticationPrincipal String email, @RequestBody AddressRequest addressRequest){

        AddressResponse response = addressService.addOrUpdateAddress(email, addressRequest);
        return ResponseEntity.ok().body(response);
    }

}
