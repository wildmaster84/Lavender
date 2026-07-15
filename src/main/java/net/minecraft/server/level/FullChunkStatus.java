package net.minecraft.server.level;

public enum FullChunkStatus {
    BLOCK_TICKING;

    public boolean isOrAfter(FullChunkStatus other) {
        return true;
    }
}
