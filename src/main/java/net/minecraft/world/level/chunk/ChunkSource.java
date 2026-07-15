package net.minecraft.world.level.chunk;

public interface ChunkSource {
    net.minecraft.world.level.chunk.ChunkAccess getChunkNow(int x, int z);
    boolean hasChunk(int x, int z);
}
