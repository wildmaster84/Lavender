package net.minecraft.world.level.block;

public enum Mirror {
    NONE,
    LEFT_RIGHT,
    FRONT_BACK;

    public static Mirror fromName(String name) {
        try {
            return Mirror.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return NONE;
        }
    }
}
