package com.CapStone.BlinkIt_BackEnd.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;


}
