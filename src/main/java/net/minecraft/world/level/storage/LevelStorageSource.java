package net.minecraft.world.level.storage;

import java.nio.file.Path;

public class LevelStorageSource {
    public static LevelStorageSource createDefault(Path path) {
        return new LevelStorageSource();
    }

    public LevelStorageAccess createAccess(String name) {
        return new LevelStorageAccess();
    }

    public static class LevelStorageAccess implements AutoCloseable {
        public java.nio.file.Path getDimensionPath(net.minecraft.resources.ResourceKey key) {
            return null;
        }

        @Override
        public void close() {
        }
    }
}
