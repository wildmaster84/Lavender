package net.minecraft.resources;

public final class Identifier {
    private final String namespace;
    private final String path;

    public Identifier(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Identifier)) return false;
        Identifier other = (Identifier) obj;
        return namespace.equals(other.namespace) && path.equals(other.path);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(namespace, path);
    }

    public static Identifier parse(String input) {
        int idx = input.indexOf(':');
        if (idx < 0) return new Identifier("minecraft", input);
        return new Identifier(input.substring(0, idx), input.substring(idx + 1));
    }

    public static Identifier tryParse(String input) {
        if (input == null || input.isEmpty()) return null;
        int idx = input.indexOf(':');
        if (idx < 0) return new Identifier("minecraft", input);
        return new Identifier(input.substring(0, idx), input.substring(idx + 1));
    }
}
