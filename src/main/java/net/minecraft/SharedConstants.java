package net.minecraft;

import me.wildmaster84.adapter.Lavender;

public class SharedConstants {
    private static final WorldVersion CURRENT_VERSION = () -> new net.minecraft.world.level.storage.DataVersion(Lavender.DATA_VERSION);

    public static WorldVersion getCurrentVersion() {
        return CURRENT_VERSION;
    }
}
