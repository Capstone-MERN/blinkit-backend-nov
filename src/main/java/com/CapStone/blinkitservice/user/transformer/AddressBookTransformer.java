package com.CapStone.blinkitservice.user.transformer;
import com.CapStone.blinkitservice.user.entity.AddressBookEntity;
import com.CapStone.blinkitservice.user.model.AddressBookRequest;
import com.CapStone.blinkitservice.user.model.AddressBookResponse;

public class AddressBookTransformer {

    public static AddressBookEntity addressBookRequestToAddressBook(AddressBookRequest addressBookRequest){
        return AddressBookEntity.builder()
                .latitude(addressBookRequest.getLatitude())
                .longitude(addressBookRequest.getLongitude())
                .addressLine1(addressBookRequest.getAddressLine1())
                .addressLine2(addressBookRequest.getAddressLine2())
                .addressLine3(addressBookRequest.getAddressLine3())
                .build();
    }

    public static AddressBookResponse addressBookToAddressBookResponse(AddressBookEntity addressBook){
        return AddressBookResponse.builder()
                .latitude(addressBook.getLatitude())
                .longitude(addressBook.getLongitude())
                .addressLine1(addressBook.getAddressLine1())
                .addressLine2(addressBook.getAddressLine2())
                .addressLine3(addressBook.getAddressLine3())
                .userResponse(UserTransformer.userToUserResponse(addressBook.getUserEntity()))
                .build();
    }
}
