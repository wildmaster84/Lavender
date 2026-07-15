package net.minecraft.world.level.block;

public enum Rotation {
    NONE,
    CLOCKWISE_90,
    CLOCKWISE_180,
    COUNTERCLOCKWISE_90;

    public static Rotation fromName(String name) {
        try {
            return Rotation.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return NONE;
        }
    }
}
