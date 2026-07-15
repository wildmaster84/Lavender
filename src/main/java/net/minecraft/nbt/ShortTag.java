package net.minecraft.nbt;

public class ShortTag implements Tag {
    private final short value;

    public ShortTag(short value) {
        this.value = value;
    }

    public static ShortTag valueOf(short value) {
        return new ShortTag(value);
    }

    public short shortValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 2;
    }
}
