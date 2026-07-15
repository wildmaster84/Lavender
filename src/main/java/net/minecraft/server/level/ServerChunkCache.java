package net.minecraft.server.level;

import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;

public class ServerChunkCache implements net.minecraft.world.level.chunk.ChunkSource {
    public ChunkMap chunkMap;
    public Object mainThreadProcessor;
    private final net.minestom.server.instance.Instance instance;

    public ServerChunkCache() {
        this.instance = null;
    }

    public ServerChunkCache(net.minestom.server.instance.Instance instance) {
        this.instance = instance;
    }

    public ChunkGenerator getGenerator() {
        return null;
    }

    public Object getChunkFutureMainThread(int x, int z, ChunkStatus status, boolean create) {
        return null;
    }

    public ThreadedLevelLightEngine getLightEngine() {
        return new ThreadedLevelLightEngine();
    }

    public void blockChanged(net.minecraft.core.BlockPos pos) {
    }

    public void close(boolean flag) {
    }

    public net.minecraft.world.level.levelgen.RandomState randomState() {
        return null;
    }

    @Override
    public ChunkAccess getChunkNow(int x, int z) {
        if (instance == null) return null;
        if (!instance.isChunkLoaded(x, z)) return null;
        return new LevelChunk(instance, x, z);
    }

    @Override
    public boolean hasChunk(int x, int z) {
        if (instance == null) return false;
        return instance.isChunkLoaded(x, z);
    }
}
