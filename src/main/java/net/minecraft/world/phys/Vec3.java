package net.minecraft.world.phys;

import net.minecraft.core.Vec3i;

public class Vec3 {
    public static final Vec3 ORIGIN = new Vec3(0.0, 0.0, 0.0);

    private final double x;
    private final double y;
    private final double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public static Vec3 atLowerCornerOf(Vec3i vec) {
        return new Vec3(vec.getX(), vec.getY(), vec.getZ());
    }
}
