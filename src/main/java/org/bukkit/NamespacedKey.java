package org.bukkit;

public class NamespacedKey {
    private final String namespace;
    private final String key;

    public NamespacedKey(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    public NamespacedKey(org.bukkit.plugin.Plugin plugin, String key) {
        this.namespace = plugin.getName().toLowerCase();
        this.key = key;
    }

    public String getNamespace() { return namespace; }
    public String getKey() { return key; }
    @Override public String toString() { return namespace + ":" + key; }
    @Override public boolean equals(Object obj) {
        if (!(obj instanceof NamespacedKey)) return false;
        NamespacedKey other = (NamespacedKey) obj;
        return namespace.equals(other.namespace) && key.equals(other.key);
    }
    @Override public int hashCode() { return java.util.Objects.hash(namespace, key); }
}
