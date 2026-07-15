package net.minecraft.core;

import java.util.Optional;

import com.mojang.serialization.DynamicOps;

import net.minecraft.resources.ResourceKey;

public interface RegistryAccess {
    <T> Registry<T> lookupOrThrow(ResourceKey<T> key);

    <T> Optional<Registry<T>> lookup(ResourceKey<T> key);

    interface Frozen extends RegistryAccess {
        net.minecraft.resources.RegistryOps createSerializationContext(DynamicOps<?> ops);
    }
}
