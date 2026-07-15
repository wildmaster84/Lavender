package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkSource;

public interface LevelAccessor extends LevelReader {
    BlockState setBlock(BlockPos pos, BlockState state, int flags, boolean isMoving);
    boolean setBlock(BlockPos pos, BlockState state, int flags);
    void scheduleTick(BlockPos pos, Block block, int delay);
    void scheduleTick(BlockPos pos, Block block, int delay, net.minecraft.tick.TickPriority priority);
    ChunkSource getChunkSource();
    LevelAccessor getLevel();
}
