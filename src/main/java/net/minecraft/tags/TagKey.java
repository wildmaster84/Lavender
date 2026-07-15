package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public final class TagKey<T> {
    private final ResourceKey<T> registry;
    private final Identifier location;

    public TagKey(ResourceKey<T> registry, Identifier location) {
        this.registry = registry;
        this.location = location;
    }

    public Identifier location() {
        return location;
    }

    public ResourceKey<T> registry() {
        return registry;
    }

    @Override
    public String toString() {
        return "#" + location;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TagKey)) return false;
        TagKey<?> other = (TagKey<?>) obj;
        return java.util.Objects.equals(registry, other.registry) && location.equals(other.location);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(registry, location);
    }
}
