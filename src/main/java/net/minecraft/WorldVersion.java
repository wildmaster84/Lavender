package net.minecraft;

public class WorldVersion {
    public static final WorldVersion INSTANCE = new WorldVersion();

    public int getProtocolVersion() {
        return me.wildmaster84.adapter.Lavender.PROTOCOL_VERSION;
    }
}
