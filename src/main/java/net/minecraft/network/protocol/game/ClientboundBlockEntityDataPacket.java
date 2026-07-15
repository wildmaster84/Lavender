package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class ClientboundBlockEntityDataPacket {
    private final BlockPos pos;
    private final int type;
    private final CompoundTag tag;

    public ClientboundBlockEntityDataPacket(BlockPos pos, int type, CompoundTag tag) {
        this.pos = pos;
        this.type = type;
        this.tag = tag;
    }

    public static ClientboundBlockEntityDataPacket create(net.minecraft.world.level.block.entity.BlockEntity blockEntity) {
        return new ClientboundBlockEntityDataPacket(new BlockPos(0, 0, 0), 0, new CompoundTag());
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getType() {
        return type;
    }

    public CompoundTag getTag() {
        return tag;
    }
}
