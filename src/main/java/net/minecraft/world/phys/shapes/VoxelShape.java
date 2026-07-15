package net.minecraft.world.phys.shapes;

import net.minecraft.core.Direction;

public class VoxelShape {
    public static final VoxelShape EMPTY = new VoxelShape();
    public static final VoxelShape FULL = new VoxelShape();

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public double max(Direction.Axis axis) {
        return 1.0;
    }

    public double min(Direction.Axis axis) {
        return 0.0;
    }
}
