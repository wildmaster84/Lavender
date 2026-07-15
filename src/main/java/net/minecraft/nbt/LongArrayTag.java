package net.minecraft.nbt;

public class LongArrayTag implements Tag {
    private final long[] data;

    public LongArrayTag(long[] data) {
        this.data = data;
    }

    @Override
    public byte getId() {
        return 12;
    }
}
