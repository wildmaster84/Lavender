package net.minecraft.nbt;

public class IntArrayTag implements Tag {
    private final int[] data;

    public IntArrayTag(int[] data) {
        this.data = data;
    }

    @Override
    public byte getId() {
        return 11;
    }
}
