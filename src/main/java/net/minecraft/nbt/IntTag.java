package net.minecraft.nbt;

public class IntTag implements Tag {
    private final int value;

    public IntTag(int value) {
        this.value = value;
    }

    public static IntTag valueOf(int value) {
        return new IntTag(value);
    }

    public int intValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 3;
    }
}
