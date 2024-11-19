package com.CapStone.BlinkIt_BackEnd.address.transformer;

import com.CapStone.BlinkIt_BackEnd.address.entity.AddressBook;
import com.CapStone.BlinkIt_BackEnd.address.model.AddressBookRequest;
import com.CapStone.BlinkIt_BackEnd.address.model.AddressBookResponse;

public class AddressBookTransformer {

    public static AddressBook addressBookRequestToAddressBook(AddressBookRequest addressBookRequest){
        return AddressBook.builder()
                .latitude(addressBookRequest.getLatitude())
                .longitude(addressBookRequest.getLongitude())
                .addressLine1(addressBookRequest.getAddressLine1())
                .addressLine2(addressBookRequest.getAddressLine2())
                .addressLine3(addressBookRequest.getAddressLine3())
                .build();
    }

    public static AddressBookResponse addressBookToAddressBookResponse(AddressBook addressBook){
        return AddressBookResponse.builder()
                .latitude(addressBook.getLatitude())
                .longitude(addressBook.getLongitude())
                .addressLine1(addressBook.getAddressLine1())
                .addressLine2(addressBook.getAddressLine2())
                .addressLine3(addressBook.getAddressLine3())
                .user(addressBook.getUser())
                .build();
    }
}
