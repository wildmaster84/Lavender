package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;

public interface ItemFrame extends HangingEntity {

    ItemStack getItem();
    void setItem(ItemStack item);
    boolean hasItem();
    org.bukkit.Rotation getRotation();
    void setRotation(org.bukkit.Rotation rotation);
    org.bukkit.block.BlockFace getAttachedFace();
}
