package org.bukkit.block;

import org.bukkit.block.data.BlockData;

public interface Waterlogged extends BlockData {
    boolean isWaterlogged();
    void setWaterlogged(boolean waterlogged);
}
