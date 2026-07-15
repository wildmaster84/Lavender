package net.minecraft.core;

public interface Holder<T> {
    T value();

    interface Reference<T> extends Holder<T> {
    }
}
