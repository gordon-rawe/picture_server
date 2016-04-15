package com.gordon.rawe.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

/**
 * Created by gordon on 16/4/5.
 */
public class UUIDUtils {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        StringBuilder builder = new StringBuilder(uuid.toString());
        return builder.substring(0, 8) + builder.substring(9, 13) +
                builder.substring(14, 18) + builder.substring(19, 23) + builder.substring(24);
    }
}
