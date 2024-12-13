package com.CapStone.blinkitservice.user.Controller;

import com.CapStone.blinkitservice.user.Service.AddressService;
import com.CapStone.blinkitservice.user.dto.AddressRequest;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth/address/v1")
@RequiredArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressBookEntity addUserAddress(@AuthenticationPrincipal String email, @RequestBody AddressRequest addressRequest){
        AddressBookEntity response = addressService.addOrUpdateAddress(email, addressRequest);

        return response;
    }

}
