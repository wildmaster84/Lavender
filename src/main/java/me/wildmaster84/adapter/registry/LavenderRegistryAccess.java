package me.wildmaster84.adapter.registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.mojang.serialization.DynamicOps;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minestom.server.MinecraftServer;
import net.minestom.server.registry.RegistryKey;

public class LavenderRegistryAccess implements RegistryAccess.Frozen {

    private final Map<Identifier, LavenderRegistry<?>> registries = new LinkedHashMap<>();

    public LavenderRegistryAccess() {
        registries.put(Registries.BIOME.identifier(), createBiomeRegistry());
        registries.put(Registries.CONFIGURED_FEATURE.identifier(), new LavenderRegistry<>(Set.of()));
        registries.put(Registries.PLACED_FEATURE.identifier(), new LavenderRegistry<>(Set.of()));
        registries.put(Registries.STRUCTURE.identifier(), new LavenderRegistry<>(Set.of()));
        registries.put(Registries.BLOCK.identifier(), createBlockRegistry());
        registries.put(Registries.ITEM.identifier(), createItemRegistry());
    }

    private LavenderRegistry<?> createBiomeRegistry() {
        Set<Identifier> biomeKeys = MinecraftServer.getBiomeRegistry().keys().stream()
                .map(RegistryKey::key)
                .map(k -> new Identifier(k.namespace(), k.value()))
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
        return new LavenderRegistry<>(biomeKeys);
    }

    @SuppressWarnings("unchecked")
    private LavenderRegistry<?> createBlockRegistry() {
        Set<Identifier> blockKeys = new java.util.LinkedHashSet<>();
        LavenderRegistry<net.minecraft.world.level.block.Block> registry = new LavenderRegistry<>(blockKeys, (ResourceKey<net.minecraft.world.level.block.Block>) (ResourceKey<?>) Registries.BLOCK);
        for (net.minestom.server.instance.block.Block msBlock : net.minestom.server.instance.block.Block.values()) {
            String name = msBlock.name();
            Identifier id = new Identifier(
                    name.startsWith("minecraft:") ? name.substring(0, name.indexOf(':')) : "minecraft",
                    name.startsWith("minecraft:") ? name.substring(name.indexOf(':') + 1) : name
            );
            blockKeys.add(id);
            registry.put(id, net.minecraft.world.level.block.Block.of(msBlock));
        }
        return registry;
    }

    @SuppressWarnings("unchecked")
    private LavenderRegistry<?> createItemRegistry() {
        Set<Identifier> itemKeys = new java.util.LinkedHashSet<>();
        LavenderRegistry<net.minecraft.world.item.Item> registry = new LavenderRegistry<>(itemKeys, (ResourceKey<net.minecraft.world.item.Item>) (ResourceKey<?>) Registries.ITEM);
        for (net.minestom.server.item.Material mat : net.minestom.server.item.Material.values()) {
            String name = mat.name();
            Identifier id = new Identifier(
                    name.startsWith("minecraft:") ? name.substring(0, name.indexOf(':')) : "minecraft",
                    name.startsWith("minecraft:") ? name.substring(name.indexOf(':') + 1) : name
            );
            itemKeys.add(id);
            registry.put(id, new net.minecraft.world.item.Item(mat));
        }
        return registry;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Registry<T> lookupOrThrow(ResourceKey<T> key) {
        LavenderRegistry<?> reg = registries.get(key.identifier());
        if (reg == null) throw new java.util.NoSuchElementException("No registry for key " + key);
        return (LavenderRegistry<T>) reg;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<Registry<T>> lookup(ResourceKey<T> key) {
        LavenderRegistry<?> reg = registries.get(key.identifier());
        if (reg == null) return Optional.empty();
        return Optional.of((LavenderRegistry<T>) reg);
    }

    @Override
    public RegistryOps createSerializationContext(DynamicOps<?> ops) {
        return new RegistryOps();
    }
}
