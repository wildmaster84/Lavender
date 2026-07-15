package net.minecraft.nbt;

public class EndTag implements Tag {
    public static final EndTag INSTANCE = new EndTag();

    @Override
    public byte getId() {
        return 0;
    }
}
