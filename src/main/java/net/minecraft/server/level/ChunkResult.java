package net.minecraft.server.level;

import java.util.Optional;

public class ChunkResult {
    public <T> T orElse(T defaultValue) {
        return defaultValue;
    }
}
