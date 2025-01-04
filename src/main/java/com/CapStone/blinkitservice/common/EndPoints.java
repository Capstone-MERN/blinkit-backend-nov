package com.CapStone.blinkitservice.common;

public class EndPoints {

    public static String[] publicEndPoints={"/auth/signin","/auth/signup","/category/all","/collections/getActiveCollections","/products/v1/search","/products/v1/details"};

    public static boolean isPublicEndPoint(String endPoint){
        for (String point:publicEndPoints){
            if(point.equals(endPoint)){
                return true;
            }
        }
        return false;
    }

}
