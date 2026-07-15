package com.mojang.serialization;

public abstract class MapCodec<T> {
    public abstract Codec<T> codec();

    public static <T> MapCodec<T> simple(Codec<T> codec) {
        return new MapCodec<T>() {
            @Override
            public Codec<T> codec() {
                return codec;
            }
        };
    }
}
