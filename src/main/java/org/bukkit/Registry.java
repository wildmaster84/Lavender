package org.bukkit;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Registry<T extends Keyed> extends Iterable<T> {

    T get(NamespacedKey key);

    void forEach(Consumer<? super T> consumer);

    T getFirst();

    Stream<T> stream();

    Registry<Material> MATERIAL = SimpleRegistry.fromEnum(Material.class);
    Registry<org.bukkit.block.Biome> BIOME = SimpleRegistry.fromEnum(org.bukkit.block.Biome.class);
    Registry<org.bukkit.entity.EntityType> ENTITY_TYPE = SimpleRegistry.fromEnum(org.bukkit.entity.EntityType.class);
    Registry<?> SOUND = SimpleRegistry.empty();
    Registry<?> STATISTIC = SimpleRegistry.empty();
    Registry<?> STRUCTURE_TYPE = SimpleRegistry.empty();
    Registry<?> POTION_EFFECT_TYPE = SimpleRegistry.empty();
    Registry<?> ENCHANTMENT = SimpleRegistry.empty();
    Registry<?> ART = SimpleRegistry.empty();
    Registry<?> VILLAGER_PROFESSION = SimpleRegistry.empty();
    Registry<?> VILLAGER_TYPE = SimpleRegistry.empty();
    Registry<?> MEMORY_MODULE_KEY = SimpleRegistry.empty();
    Registry<?> SENSOR_TYPE = SimpleRegistry.empty();
    Registry<?> PARTICLE = SimpleRegistry.empty();
    Registry<?> FLUID = SimpleRegistry.empty();
    Registry<?> CAT_VARIANT = SimpleRegistry.empty();
    Registry<?> FROG_VARIANT = SimpleRegistry.empty();
    Registry<?> MAP_DECORATION_TYPE = SimpleRegistry.empty();
    Registry<?> BOAT_TYPE = SimpleRegistry.empty();
    Registry<?> WOLF_VARIANT = SimpleRegistry.empty();
    Registry<?> RAID_WAVE = SimpleRegistry.empty();
    Registry<?> DISPLAY_SLOT = SimpleRegistry.empty();
    Registry<?> INSTRUMENT = SimpleRegistry.empty();
    Registry<?> PAINTING_VARIANT = SimpleRegistry.empty();

    final class SimpleRegistry<T extends Keyed> implements Registry<T> {
        private final Map<NamespacedKey, T> entries;

        private SimpleRegistry(Map<NamespacedKey, T> entries) {
            this.entries = entries;
        }

        @Override
        public T get(NamespacedKey key) {
            return entries.get(key);
        }

        @Override
        public void forEach(Consumer<? super T> consumer) {
            int count = 0;
            try {
                for (T value : entries.values()) {
                    count++;
                    consumer.accept(value);
                }
            } catch (Throwable t) {
                System.out.println("[Lavender-Debug] Registry.forEach threw at item " + count + ": " + t.getClass().getName() + ": " + t.getMessage());
                t.printStackTrace();
                throw t;
            }
        }

        @Override
        public T getFirst() {
            Iterator<T> it = entries.values().iterator();
            return it.hasNext() ? it.next() : null;
        }

        @Override
        public Stream<T> stream() {
            return entries.values().stream();
        }

        @Override
        public Iterator<T> iterator() {
            return entries.values().iterator();
        }

        public static <T extends Keyed> SimpleRegistry<T> empty() {
            return new SimpleRegistry<>(Collections.emptyMap());
        }

        public static <T extends Enum<T> & Keyed> SimpleRegistry<T> fromEnum(Class<T> enumClass) {
            Map<NamespacedKey, T> map = new LinkedHashMap<>();
            for (T value : enumClass.getEnumConstants()) {
                map.put(value.getKey(), value);
            }
            return new SimpleRegistry<>(map);
        }
    }
}
