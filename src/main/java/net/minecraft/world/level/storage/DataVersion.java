package net.minecraft.world.level.storage;

public class DataVersion {
    private final int version;

    public DataVersion(int version) {
        this.version = version;
    }

    public int version() {
        return version;
    }
}
