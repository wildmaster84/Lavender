package net.minecraft.core;

import java.util.Optional;

import net.minecraft.resources.ResourceKey;

public interface HolderLookup {
    <T> Optional<Registry<T>> lookup(ResourceKey<T> key);

    interface Provider extends HolderLookup {
    }
}
