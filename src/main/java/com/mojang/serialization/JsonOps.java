package com.mojang.serialization;

public final class JsonOps implements DynamicOps<Object> {
    public static final JsonOps INSTANCE = new JsonOps();

    private JsonOps() {
    }
}
