package org.bukkit.block;

import org.bukkit.block.BlockState;
import org.bukkit.inventory.InventoryHolder;

public interface DoubleChest extends BlockState, InventoryHolder {
    Chest getLeftSide();
    Chest getRightSide();
}
