package net.minecraft.nbt;

public class DoubleTag implements Tag {
    private final double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    public static DoubleTag valueOf(double value) {
        return new DoubleTag(value);
    }

    public double doubleValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 6;
    }
}
