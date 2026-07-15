package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;

public class BlockEntity {
    private BlockPos pos;
    private BlockState state;
    private Level level;
    private boolean removed;

    public BlockEntity() {
    }

    public BlockEntity(BlockPos pos, BlockState state) {
        this.pos = pos;
        this.state = state;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setBlockState(BlockState state) {
        this.state = state;
    }

    public BlockState getBlockState() {
        return state != null ? state : new BlockState();
    }

    public BlockPos getBlockPos() {
        return pos != null ? pos : new BlockPos(0, 0, 0);
    }

    public void setBlockPos(BlockPos pos) {
        this.pos = pos;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved() {
        this.removed = true;
    }

    public void clearRemoved() {
        this.removed = false;
    }

    public void saveWithId(ValueOutput output) {
    }

    public void load(ValueInput input) {
    }

    public void saveAdditional(ValueOutput output) {
    }

    public void loadAdditional(ValueInput input) {
    }

    public net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket getUpdatePacket() {
        return null;
    }

    public net.minecraft.nbt.CompoundTag getUpdateTag() {
        return new net.minecraft.nbt.CompoundTag();
    }

    public String getType() {
        return "minecraft:block_entity";
    }
}
