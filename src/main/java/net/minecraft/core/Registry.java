package net.minecraft.core;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public interface Registry<T> extends Iterable<T> {
    Optional<Holder<T>> get(Identifier identifier);

    Optional<HolderSet.Named<T>> get(TagKey<T> tag);

    Optional<Holder.Reference<T>> get(ResourceKey<T> key);

    Holder.Reference<T> getOrThrow(ResourceKey<T> key);

    T getValue(Identifier identifier);

    Identifier getKey(T value);

    Set<Identifier> keySet();

    Stream<HolderSet.Named<T>> getTags();

    Holder<T> wrapAsHolder(T value);
}
