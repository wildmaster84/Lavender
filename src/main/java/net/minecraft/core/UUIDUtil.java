package net.minecraft.core;

import java.util.UUID;

public class UUIDUtil {
    public static UUID read(com.mojang.serialization.Dynamic<?> dynamic) {
        return UUID.randomUUID();
    }

    public static UUID fromString(String s) {
        try {
            return UUID.fromString(s);
        } catch (Exception e) {
            return UUID.randomUUID();
        }
    }
}
