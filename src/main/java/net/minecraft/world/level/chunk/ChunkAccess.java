package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ChunkAccess {
    private ChunkPos pos;

    public ChunkPos getPos() {
        if (pos != null) return pos;
        return new ChunkPos(0, 0);
    }

    public void setPos(ChunkPos pos) {
        this.pos = pos;
    }

    public BlockState getBlockState(BlockPos pos) {
        return new BlockState();
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }

    public Holder getNoiseBiome(int x, int y, int z) {
        return null;
    }
}
