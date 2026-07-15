package net.minecraft.core;

import java.util.UUID;

public class UUIDUtil {
    public static UUID read(com.mojang.serialization.Dynamic<?> dynamic) {
        if (dynamic == null) return null;
        String str = dynamic.asString().result().orElse(null);
        if (str == null) return null;
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static UUID fromString(String s) {
        try {
            return UUID.fromString(s);
        } catch (Exception e) {
            return UUID.randomUUID();
        }
    }
}
