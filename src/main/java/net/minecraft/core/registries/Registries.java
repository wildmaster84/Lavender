package net.minecraft.core.registries;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public final class Registries {
    private Registries() {
    }

    public static final ResourceKey<?> BLOCK = ResourceKey.createRoot(new Identifier("minecraft", "block"));
    public static final ResourceKey<?> ITEM = ResourceKey.createRoot(new Identifier("minecraft", "item"));
    public static final ResourceKey<?> BIOME = ResourceKey.createRoot(new Identifier("minecraft", "biome"));
    public static final ResourceKey<?> ENTITY_TYPE = ResourceKey.createRoot(new Identifier("minecraft", "entity_type"));
    public static final ResourceKey<?> CONFIGURED_FEATURE = ResourceKey.createRoot(new Identifier("minecraft", "configured_feature"));
    public static final ResourceKey<?> STRUCTURE = ResourceKey.createRoot(new Identifier("minecraft", "structure"));
    public static final ResourceKey<?> PLACED_FEATURE = ResourceKey.createRoot(new Identifier("minecraft", "placed_feature"));
}
