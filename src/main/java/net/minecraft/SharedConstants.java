package net.minecraft;

public class SharedConstants {
    private static final WorldVersion CURRENT_VERSION = () -> new net.minecraft.world.level.storage.DataVersion(4790);

    public static WorldVersion getCurrentVersion() {
        return CURRENT_VERSION;
    }
}
