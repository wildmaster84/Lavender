package net.minecraft.core;

public class BlockPos extends Vec3i {
    public static final BlockPos ZERO = new BlockPos(0, 0, 0);

    public BlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public BlockPos west() {
        return new BlockPos(getX() - 1, getY(), getZ());
    }

    public BlockPos east() {
        return new BlockPos(getX() + 1, getY(), getZ());
    }

    public BlockPos below() {
        return new BlockPos(getX(), getY() - 1, getZ());
    }

    public BlockPos above() {
        return new BlockPos(getX(), getY() + 1, getZ());
    }

    public BlockPos north() {
        return new BlockPos(getX(), getY(), getZ() - 1);
    }

    public BlockPos south() {
        return new BlockPos(getX(), getY(), getZ() + 1);
    }
}
