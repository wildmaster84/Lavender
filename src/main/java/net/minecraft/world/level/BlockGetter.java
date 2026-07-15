package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.FluidState;

public interface BlockGetter {
    BlockState getBlockState(BlockPos pos);
    FluidState getFluidState(BlockPos pos);
    int getLightEmission(BlockPos pos);
    int getMaxLightLevel();
    VoxelShape getBlockState(BlockState state, BlockPos pos);
    BlockEntity getBlockEntity(BlockPos pos);
}
