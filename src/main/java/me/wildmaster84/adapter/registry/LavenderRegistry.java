package me.wildmaster84.adapter.registry;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public class LavenderRegistry<T> implements Registry<T> {

    private final Set<Identifier> keySet;
    private final Map<Identifier, T> values;
    private final Map<ResourceKey<T>, Holder.Reference<T>> references = new LinkedHashMap<>();
    private final ResourceKey<T> registryKey;

    public LavenderRegistry(Set<Identifier> keySet) {
        this(keySet, null);
    }

    public LavenderRegistry(Set<Identifier> keySet, ResourceKey<T> registryKey) {
        this.keySet = Collections.unmodifiableSet(keySet);
        this.values = new LinkedHashMap<>();
        this.registryKey = registryKey;
    }

    public void put(Identifier key, T value) {
        values.put(key, value);
        if (registryKey != null) {
            ResourceKey<T> resourceKey = ResourceKey.create(registryKey, key);
            references.put(resourceKey, new SimpleReference<>(value));
        }
    }

    @Override
    public Optional<Holder<T>> get(Identifier identifier) {
        T value = values.get(identifier);
        if (value == null) return Optional.empty();
        return Optional.of(new SimpleReference<>(value));
    }

    @Override
    public Optional<HolderSet.Named<T>> get(TagKey<T> tag) {
        return Optional.empty();
    }

    @Override
    public Optional<Holder.Reference<T>> get(ResourceKey<T> key) {
        Holder.Reference<T> ref = references.get(key);
        if (ref == null) {
            T value = values.get(key.identifier());
            if (value != null) {
                return Optional.of(new SimpleReference<>(value));
            }
        }
        return Optional.ofNullable(ref);
    }

    @Override
    public Holder.Reference<T> getOrThrow(ResourceKey<T> key) {
        Holder.Reference<T> ref = references.get(key);
        if (ref == null) {
            T value = values.get(key.identifier());
            if (value != null) {
                return new SimpleReference<>(value);
            }
        }
        if (ref == null) throw new java.util.NoSuchElementException("No value for key " + key);
        return ref;
    }

    @Override
    public T getValue(Identifier identifier) {
        return values.get(identifier);
    }

    @Override
    public Identifier getKey(T value) {
        for (Map.Entry<Identifier, T> entry : values.entrySet()) {
            if (entry.getValue() == value) return entry.getKey();
        }
        return null;
    }

    @Override
    public Set<Identifier> keySet() {
        return keySet;
    }

    @Override
    public Stream<HolderSet.Named<T>> getTags() {
        return Stream.empty();
    }

    @Override
    public Holder<T> wrapAsHolder(T value) {
        return new SimpleReference<>(value);
    }

    @Override
    public Iterator<T> iterator() {
        return values.values().iterator();
    }

    private static class SimpleReference<T> implements Holder.Reference<T> {
        private final T value;

        SimpleReference(T value) {
            this.value = value;
        }

        @Override
        public T value() {
            return value;
        }
    }
}
