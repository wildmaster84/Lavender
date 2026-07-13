package org.bukkit.entity;

import org.bukkit.inventory.Inventory;

public interface HumanEntity extends LivingEntity {
    String getName();
    org.bukkit.inventory.PlayerInventory getInventory();
    org.bukkit.inventory.Inventory getEnderChest();
    org.bukkit.inventory.ItemStack getItemInHand();
    void setItemInHand(org.bukkit.inventory.ItemStack item);
    org.bukkit.inventory.ItemStack getItemOnCursor();
    void setItemOnCursor(org.bukkit.inventory.ItemStack item);
    int getSleepTicks();
    org.bukkit.GameMode getGameMode();
    void setGameMode(org.bukkit.GameMode mode);
    boolean isBlocking();
    int getExpToLevel();
    float getExp();
    void setExp(float exp);
    int getLevel();
    void setLevel(int level);
    void giveExp(int exp);
    boolean isSleeping();
    org.bukkit.Location getBedLocation();
    void closeInventory();
    org.bukkit.inventory.InventoryView getOpenInventory();
    org.bukkit.inventory.InventoryView openInventory(Inventory inventory);
    void updateInventory();
}