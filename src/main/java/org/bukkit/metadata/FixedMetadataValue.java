package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;

public class FixedMetadataValue implements MetadataValue {

    private final Plugin owningPlugin;
    private final Object value;

    public FixedMetadataValue(Plugin owningPlugin, Object value) {
        this.owningPlugin = owningPlugin;
        this.value = value;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public int asInt() {
        if (value instanceof Number number) return number.intValue();
        if (value instanceof String string) return Integer.parseInt(string);
        return 0;
    }

    @Override
    public float asFloat() {
        if (value instanceof Number number) return number.floatValue();
        if (value instanceof String string) return Float.parseFloat(string);
        return 0.0f;
    }

    @Override
    public double asDouble() {
        if (value instanceof Number number) return number.doubleValue();
        if (value instanceof String string) return Double.parseDouble(string);
        return 0.0;
    }

    @Override
    public long asLong() {
        if (value instanceof Number number) return number.longValue();
        if (value instanceof String string) return Long.parseLong(string);
        return 0L;
    }

    @Override
    public short asShort() {
        if (value instanceof Number number) return number.shortValue();
        if (value instanceof String string) return Short.parseShort(string);
        return 0;
    }

    @Override
    public byte asByte() {
        if (value instanceof Number number) return number.byteValue();
        if (value instanceof String string) return Byte.parseByte(string);
        return 0;
    }

    @Override
    public boolean asBoolean() {
        if (value instanceof Boolean bool) return bool;
        if (value instanceof String string) return Boolean.parseBoolean(string);
        return false;
    }

    @Override
    public String asString() {
        return value != null ? value.toString() : "";
    }

    @Override
    public Plugin getOwningPlugin() {
        return owningPlugin;
    }

    @Override
    public void invalidate() {}
}
