package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface LevelReader extends BlockGetter {
    int getMinY();
    int getMaxY();
    int getHeight();
    ChunkAccess getChunk(int x, int z);
    ChunkAccess getChunkNow(int x, int z);
    boolean hasChunk(int x, int z);
    boolean isClientSide();
    int getSeaLevel();
}
