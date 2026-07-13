package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public interface BlockState {
    World getWorld();
    Location getLocation();
    int getX();
    int getY();
    int getZ();
    Material getType();
    void setType(Material type);
    byte getData();
    Block getBlock();
    boolean update();
    boolean update(boolean force);
    org.bukkit.block.data.BlockData getBlockData();
    void setBlockData(org.bukkit.block.data.BlockData blockData);
}
