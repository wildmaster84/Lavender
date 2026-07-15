package com.mojang.serialization;

import java.util.Optional;
import java.util.function.Function;

public interface DataResult<T> {
    T getOrThrow();
    T getOrThrow(Function<String, ? extends RuntimeException> exceptionFactory);
    Optional<T> result();
    Optional<String> error();

    static <T> DataResult<T> success(T value) {
        return new DataResult<T>() {
            private final T val = value;
            @Override public T getOrThrow() { return val; }
            @Override public T getOrThrow(Function<String, ? extends RuntimeException> f) { return val; }
            @Override public Optional<T> result() { return Optional.ofNullable(val); }
            @Override public Optional<String> error() { return Optional.empty(); }
        };
    }

    static <T> DataResult<T> error(String message) {
        return new DataResult<T>() {
            private final String err = message;
            @Override public T getOrThrow() { throw new RuntimeException(err); }
            @Override public T getOrThrow(Function<String, ? extends RuntimeException> f) { throw f.apply(err); }
            @Override public Optional<T> result() { return Optional.empty(); }
            @Override public Optional<String> error() { return Optional.of(err); }
        };
    }
}
