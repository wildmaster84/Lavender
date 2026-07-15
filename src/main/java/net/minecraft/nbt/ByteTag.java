package net.minecraft.nbt;

public class ByteTag implements Tag {
    private final byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public static ByteTag valueOf(byte value) {
        return new ByteTag(value);
    }

    public byte byteValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 1;
    }
}
