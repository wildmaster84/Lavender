package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;

public class Level implements LevelAccessor {
    private boolean isClientSide = false;

    public boolean isClientSide() {
        return isClientSide;
    }

    public int getMinY() {
        return -64;
    }

    public int getMaxY() {
        return 319;
    }

    public int getHeight() {
        return 384;
    }

    public int getSeaLevel() {
        return 63;
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.getBlockState(pos);
        return new BlockState();
    }

    @Override
    public boolean setBlock(BlockPos pos, BlockState state, int flags) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.setBlock(pos, state, flags);
        return false;
    }

    @Override
    public BlockState setBlock(BlockPos pos, BlockState state, int flags, boolean isMoving) {
        return state;
    }

    @Override
    public void scheduleTick(BlockPos pos, net.minecraft.world.level.block.Block block, int delay) {
    }

    @Override
    public void scheduleTick(BlockPos pos, net.minecraft.world.level.block.Block block, int delay, net.minecraft.tick.TickPriority priority) {
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkSource getChunkSource() {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.getChunkSource();
        return null;
    }

    @Override
    public LevelAccessor getLevel() {
        return this;
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkAccess getChunk(int x, int z) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.getChunk(x, z);
        return null;
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkAccess getChunkNow(int x, int z) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.getChunkNow(x, z);
        return null;
    }

    @Override
    public boolean hasChunk(int x, int z) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) {
            net.minestom.server.instance.Instance inst = sl.getMinestomInstance();
            return inst != null && inst.isChunkLoaded(x, z);
        }
        return false;
    }

    @Override
    public net.minecraft.world.level.material.FluidState getFluidState(BlockPos pos) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) {
            net.minestom.server.instance.Instance inst = sl.getMinestomInstance();
            if (inst != null) {
                net.minestom.server.instance.block.Block block = inst.getBlock(pos.getX(), pos.getY(), pos.getZ());
                return new net.minecraft.world.level.material.FluidState(block);
            }
        }
        return new net.minecraft.world.level.material.FluidState();
    }

    @Override
    public int getLightEmission(BlockPos pos) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) {
            net.minestom.server.instance.Instance inst = sl.getMinestomInstance();
            if (inst != null) {
                net.minestom.server.instance.block.Block block = inst.getBlock(pos.getX(), pos.getY(), pos.getZ());
                return block != null ? (int) block.registry().lightEmission() : 0;
            }
        }
        return 0;
    }

    @Override
    public int getMaxLightLevel() {
        return 15;
    }

    @Override
    public net.minecraft.world.phys.shapes.VoxelShape getBlockState(BlockState state, BlockPos pos) {
        return net.minecraft.world.phys.shapes.VoxelShape.EMPTY;
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        if (this instanceof net.minecraft.server.level.ServerLevel sl) return sl.getBlockEntity(pos);
        return null;
    }
}
