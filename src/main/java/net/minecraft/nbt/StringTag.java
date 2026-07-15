package net.minecraft.nbt;

public class StringTag implements Tag {
    private final String value;

    public StringTag(String value) {
        this.value = value;
    }

    public static StringTag valueOf(String value) {
        return new StringTag(value);
    }

    public String value() {
        return value;
    }

    @Override
    public byte getId() {
        return 8;
    }
}
