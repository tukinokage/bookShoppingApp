package com.example.bookshoppingapp.util;

import java.util.UUID;

public class UUIDutil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
