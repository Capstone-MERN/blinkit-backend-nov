package com.CapStone.blinkitservice.common;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringConstants {

    public static String EMAIL = "email";
    public static String PRIVATEKEY = Base64.getEncoder().encodeToString(
            "accio_capstone_blinkit_clone".getBytes(StandardCharsets.UTF_8)
    );
}
