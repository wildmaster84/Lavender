package net.minecraft.stats;

public class Stat<T> {
    private final T type;
    private final int value;

    public Stat(T type, int value) {
        this.type = type;
        this.value = value;
    }

    public T getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
