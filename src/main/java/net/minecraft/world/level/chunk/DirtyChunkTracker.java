package net.minecraft.world.level.chunk;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DirtyChunkTracker {
    private static final Map<net.minestom.server.instance.Instance, Set<Long>> dirtyChunks = new ConcurrentHashMap<>();

    private static long key(int chunkX, int chunkZ) {
        return ((long) chunkX << 32) | (chunkZ & 0xFFFFFFFFL);
    }

    public static void markDirty(net.minestom.server.instance.Instance instance, int chunkX, int chunkZ) {
        dirtyChunks.computeIfAbsent(instance, k -> ConcurrentHashMap.newKeySet()).add(key(chunkX, chunkZ));
    }

    public static Set<Long> drain(net.minestom.server.instance.Instance instance) {
        Set<Long> set = dirtyChunks.remove(instance);
        return set != null ? set : Set.of();
    }
}
