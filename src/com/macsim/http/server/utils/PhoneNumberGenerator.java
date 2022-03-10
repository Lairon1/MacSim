package com.macsim.http.server.utils;

import java.util.Random;

public class PhoneNumberGenerator {

    public static String generate(){
        Random random = new Random();
        StringBuilder number = new StringBuilder("8777");
        for (int i = 0; i < 7; i++) number.append(random.nextInt(9));
        return number.toString();
    }

}
