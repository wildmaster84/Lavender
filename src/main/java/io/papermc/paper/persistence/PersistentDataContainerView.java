package io.papermc.paper.persistence;

public interface PersistentDataContainerView {
    <T> T get(org.bukkit.NamespacedKey key, org.bukkit.persistence.PersistentDataType<T> dataType);
    <T> T getOrDefault(org.bukkit.NamespacedKey key, org.bukkit.persistence.PersistentDataType<T> dataType, T defaultValue);
    <T> boolean has(org.bukkit.NamespacedKey key, org.bukkit.persistence.PersistentDataType<T> dataType);
    boolean has(org.bukkit.NamespacedKey key);
    boolean isEmpty();
    java.util.Set<org.bukkit.NamespacedKey> getKeys();
}
