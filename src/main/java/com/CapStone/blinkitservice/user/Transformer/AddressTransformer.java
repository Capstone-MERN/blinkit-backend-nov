package com.CapStone.blinkitservice.user.Transformer;

import com.CapStone.blinkitservice.user.dto.AddressRequest;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;

public class AddressTransformer {
    public static AddressBookEntity addressRequestToAddress(AddressRequest addressRequest) {
        return AddressBookEntity.builder()
                .addressLine1(addressRequest.getAddressLine1())
                .addressLine2(addressRequest.getAddressLine2())
                .addressLine3(addressRequest.getAddressLine3())
                .longitude(addressRequest.getLongitude())
                .latitude(addressRequest.getLatitude())
                .phone_no(addressRequest.getPhoneNo())
                .build();
    }
}
