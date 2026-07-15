package net.minecraft.nbt;

public class LongTag implements Tag {
    private final long value;

    public LongTag(long value) {
        this.value = value;
    }

    public static LongTag valueOf(long value) {
        return new LongTag(value);
    }

    public long longValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 4;
    }
}
