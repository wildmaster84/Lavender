package org.bukkit.persistence;

public interface PersistentDataContainer extends io.papermc.paper.persistence.PersistentDataContainerView {
    <T> void set(org.bukkit.NamespacedKey key, PersistentDataType<T> dataType, T value);
    <T> T get(org.bukkit.NamespacedKey key, PersistentDataType<T> dataType);
    <T> T getOrDefault(org.bukkit.NamespacedKey key, PersistentDataType<T> dataType, T defaultValue);
    <T> boolean has(org.bukkit.NamespacedKey key, PersistentDataType<T> dataType);
    boolean has(org.bukkit.NamespacedKey key);
    boolean isEmpty();
    void remove(org.bukkit.NamespacedKey key);
    java.util.Set<org.bukkit.NamespacedKey> getKeys();
}
