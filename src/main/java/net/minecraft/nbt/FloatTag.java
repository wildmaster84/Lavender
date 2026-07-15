package net.minecraft.nbt;

public class FloatTag implements Tag {
    private final float value;

    public FloatTag(float value) {
        this.value = value;
    }

    public static FloatTag valueOf(float value) {
        return new FloatTag(value);
    }

    public float floatValue() {
        return value;
    }

    @Override
    public byte getId() {
        return 5;
    }
}
