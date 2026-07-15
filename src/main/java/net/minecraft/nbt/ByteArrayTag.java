package net.minecraft.nbt;

public class ByteArrayTag implements Tag {
    private final byte[] data;

    public ByteArrayTag(byte[] data) {
        this.data = data;
    }

    public byte[] getAsByteArray() {
        return data;
    }

    @Override
    public byte getId() {
        return 7;
    }
}
