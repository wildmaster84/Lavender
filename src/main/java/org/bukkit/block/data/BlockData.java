package org.bukkit.block.data;

public interface BlockData {
    String getAsString();
    String getAsString(boolean hideUnused);
    org.bukkit.Material getMaterial();
    BlockData clone();
    boolean matches(BlockData data);
    SoundGroup getSoundGroup();
    float getDestroySpeed();
    int getLightEmission();
    boolean isOccluding();
    boolean isFaceSturdy(org.bukkit.block.BlockFace face, boolean isCorrectFace);
    boolean isPreferredTool(org.bukkit.inventory.ItemStack item);
    boolean isCollidable();
    org.bukkit.block.BlockState createBlockState();
    int getLight();
    int getOpacity();
    org.bukkit.Axis getAxis();
    boolean isFlammable();
    boolean isRandomlyTicked();
}
