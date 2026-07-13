package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public interface Block {
    World getWorld();
    Location getLocation();
    int getX();
    int getY();
    int getZ();
    Material getType();
    void setType(Material type);
    void setType(Material type, boolean applyPhysics);
    byte getData();
    void setData(byte data);
    Block getRelative(int modX, int modY, int modZ);
    Block getRelative(org.bukkit.block.BlockFace face);
    Block getRelative(org.bukkit.block.BlockFace face, int distance);
    BlockState getState();
    Biome getBiome();
    void setBiome(Biome bio);
    boolean isLiquid();
    boolean isEmpty();
    boolean isSolid();
    double getTemperature();
    double getHumidity();
    boolean isPowered();
    boolean isBlockPowered();
    boolean isBlockIndirectlyPowered();
    boolean isBlockFacePowered(BlockFace face);
    boolean isBlockFaceIndirectlyPowered(BlockFace face);
    int getBlockPower();
    int getBlockPower(BlockFace face);
    boolean breakNaturally();
    boolean applyBoneMeal(BlockFace face);
    boolean canPlace();
    void setBlockData(org.bukkit.block.data.BlockData blockData);
    org.bukkit.block.data.BlockData getBlockData();
    org.bukkit.Chunk getChunk();
}
