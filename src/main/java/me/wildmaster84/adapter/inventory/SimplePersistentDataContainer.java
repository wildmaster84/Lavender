package me.wildmaster84.adapter.inventory;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimplePersistentDataContainer implements PersistentDataContainer {
    private final Map<NamespacedKey, Object> data = new HashMap<>();

    @Override
    public <T> void set(NamespacedKey key, PersistentDataType<T> dataType, T value) {
        data.put(key, value);
    }

    @Override
    public <T> T get(NamespacedKey key, PersistentDataType<T> dataType) {
        return (T) data.get(key);
    }

    @Override
    public <T> T getOrDefault(NamespacedKey key, PersistentDataType<T> dataType, T defaultValue) {
        return (T) data.getOrDefault(key, defaultValue);
    }

    @Override
    public <T> boolean has(NamespacedKey key, PersistentDataType<T> dataType) {
        return data.containsKey(key);
    }

    @Override
    public boolean has(NamespacedKey key) {
        return data.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void remove(NamespacedKey key) {
        data.remove(key);
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return data.keySet();
    }
}
