package com.mojang.serialization;

public interface Codec<T> {
    DataResult<T> parse(DynamicOps<?> ops, Object input);

    DataResult<?> encodeStart(DynamicOps<?> ops, T input);

    default MapCodec<T> optionalFieldOf(String name, T defaultValue) {
        return MapCodec.simple(this);
    }

    static <T> Codec<T> simple() {
        return new Codec<T>() {
            @Override
            public DataResult<T> parse(DynamicOps<?> ops, Object input) {
                return DataResult.success((T) input);
            }

            @Override
            public DataResult<?> encodeStart(DynamicOps<?> ops, T input) {
                return DataResult.success(input);
            }
        };
    }
}
