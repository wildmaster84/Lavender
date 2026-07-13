package org.bukkit.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import java.util.UUID;

public interface HumanEntity extends Entity {
    String getName();
    PlayerInventory getInventory();
    ItemStack getItemInHand();
    void setItemInHand(ItemStack item);
    ItemStack getItemOnCursor();
    void setItemOnCursor(ItemStack item);
    InventoryView getOpenInventory();
    InventoryView openInventory(Inventory inventory);
    void closeInventory();
    org.bukkit.GameMode getGameMode();
    void setGameMode(org.bukkit.GameMode mode);
    boolean isSleeping();
    int getSleepTicks();
    boolean isBlocking();
    int getExpToLevel();
    void setCooldown(org.bukkit.Material material, int ticks);
    int getCooldown(org.bukkit.Material material);
    boolean hasCooldown(org.bukkit.Material material);
    UUID getUniqueId();
    Location getEyeLocation();
    double getEyeHeight();
    double getEyeHeight(boolean ignoreSneaking);
    org.bukkit.Location getBedSpawnLocation();
    void setBedSpawnLocation(org.bukkit.Location location);
    void setBedSpawnLocation(org.bukkit.Location location, boolean force);
}
