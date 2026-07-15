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
        return new BlockState();
    }

    @Override
    public boolean setBlock(BlockPos pos, BlockState state, int flags) {
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
        return null;
    }

    @Override
    public LevelAccessor getLevel() {
        return this;
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkAccess getChunk(int x, int z) {
        return null;
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkAccess getChunkNow(int x, int z) {
        return null;
    }

    @Override
    public boolean hasChunk(int x, int z) {
        return false;
    }

    @Override
    public net.minecraft.world.level.material.FluidState getFluidState(BlockPos pos) {
        return new net.minecraft.world.level.material.FluidState();
    }

    @Override
    public int getLightEmission(BlockPos pos) {
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
        return null;
    }
}
