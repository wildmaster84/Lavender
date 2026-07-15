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

    public static NamespacedKey fromString(String key) {
        if (key == null) return null;
        int idx = key.indexOf(':');
        if (idx < 0) return new NamespacedKey("minecraft", key);
        return new NamespacedKey(key.substring(0, idx), key.substring(idx + 1));
    }

    public static NamespacedKey minecraft(String key) {
        return new NamespacedKey("minecraft", key);
    }
}
