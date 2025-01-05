package com.CapStone.blinkitservice.user.Transformer;

import com.CapStone.blinkitservice.user.dto.AddressRequest;
import com.CapStone.blinkitservice.user.dto.ResponseDto.AddressResponse;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;

public class AddressTransformer {
    public static AddressBookEntity addressRequestToAddress(AddressRequest addressRequest) {
        return AddressBookEntity.builder()
                .addressLine1(addressRequest.getAddressLine1())
                .addressLine2(addressRequest.getAddressLine2())
                .addressLine3(addressRequest.getAddressLine3())
                .longitude(addressRequest.getLongitude())
                .latitude(addressRequest.getLatitude())
                .phone_no(addressRequest.getPhone_no())
                .build();
    }
    public static AddressResponse addressToAddressResponse(AddressBookEntity address){
        return AddressResponse.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .addressLine3(address.getAddressLine3())
                .longitude(address.getLongitude())
                .latitude(address.getLatitude())
                .phone_no(address.getPhone_no())
                .build();
    }
}
