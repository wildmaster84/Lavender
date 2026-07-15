package net.minecraft.resources;

public class TagKey<T> {
    private final Identifier location;

    public TagKey(Identifier location) {
        this.location = location;
    }

    public Identifier location() {
        return location;
    }
}
