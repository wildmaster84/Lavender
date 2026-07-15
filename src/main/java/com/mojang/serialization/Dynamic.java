package com.mojang.serialization;

public class Dynamic<T> {
    private final DynamicOps<T> ops;
    private final T value;

    public Dynamic(DynamicOps<T> ops, T value) {
        this.ops = ops;
        this.value = value;
    }

    public DynamicOps<T> getOps() {
        return ops;
    }

    public T getValue() {
        return value;
    }

    public DataResult<String> asString() {
        if (value instanceof String s) {
            return DataResult.success(s);
        }
        return DataResult.error("Not a string");
    }
}
