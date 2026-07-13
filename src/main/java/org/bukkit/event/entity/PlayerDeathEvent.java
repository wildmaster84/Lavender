package org.bukkit.event.entity;

import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathEvent extends EntityEvent {
    public PlayerDeathEvent(Player player, List<ItemStack> drops, int droppedExp, String deathMessage) { super(player); }
    public PlayerDeathEvent(Player player, List<ItemStack> drops, int droppedExp, String deathMessage, boolean keepInventory) { super(player); }
    public List<ItemStack> getDrops() { return new java.util.ArrayList<>(); }
    public int getDroppedExp() { return 0; }
    public void setDroppedExp(int exp) {}
    public String getDeathMessage() { return null; }
    public void setDeathMessage(String message) {}
    public boolean getKeepInventory() { return false; }
    public void setKeepInventory(boolean keepInventory) {}
    public boolean getKeepLevel() { return false; }
    public void setKeepLevel(boolean keepLevel) {}
    public int getNewExp() { return 0; }
    public void setNewExp(int exp) {}
    public int getNewLevel() { return 0; }
    public void setNewLevel(int level) {}
    public int getNewTotalExp() { return 0; }
    public void setNewTotalExp(int totalExp) {}
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}