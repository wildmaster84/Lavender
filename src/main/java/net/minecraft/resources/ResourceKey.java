package net.minecraft.resources;

public class ResourceKey<T> {
    private final ResourceKey<?> registry;
    private final Identifier identifier;

    public ResourceKey(ResourceKey<?> registry, Identifier identifier) {
        this.registry = registry;
        this.identifier = identifier;
    }

    public Identifier identifier() {
        return identifier;
    }

    public Identifier location() {
        return identifier;
    }

    public static <T> ResourceKey<T> create(ResourceKey<?> registry, Identifier identifier) {
        return new ResourceKey<T>(registry, identifier);
    }

    public static <T> ResourceKey<T> createRoot(Identifier identifier) {
        return new ResourceKey<T>(null, identifier);
    }

    @Override
    public String toString() {
        return (registry != null ? registry.identifier + "/" : "") + identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResourceKey)) return false;
        ResourceKey<?> other = (ResourceKey<?>) obj;
        return java.util.Objects.equals(registry, other.registry) && identifier.equals(other.identifier);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(registry, identifier);
    }
}
