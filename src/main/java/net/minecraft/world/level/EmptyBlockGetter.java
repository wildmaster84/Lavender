package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.FluidState;

public class EmptyBlockGetter implements BlockGetter {
    public static final EmptyBlockGetter INSTANCE = new EmptyBlockGetter();

    @Override
    public BlockState getBlockState(BlockPos pos) {
        return new BlockState();
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        return new FluidState();
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
    public VoxelShape getBlockState(BlockState state, BlockPos pos) {
        return VoxelShape.EMPTY;
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }
}
