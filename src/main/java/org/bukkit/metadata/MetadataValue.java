package org.bukkit.metadata;

public interface MetadataValue {
    Object value();
    int asInt();
    float asFloat();
    double asDouble();
    long asLong();
    short asShort();
    byte asByte();
    boolean asBoolean();
    String asString();
    org.bukkit.plugin.Plugin getOwningPlugin();
    void invalidate();
}
