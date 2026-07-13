package org.bukkit.inventory;

public interface InventoryView {
    org.bukkit.entity.HumanEntity getPlayer();
    org.bukkit.inventory.Inventory getTopInventory();
    org.bukkit.inventory.Inventory getBottomInventory();
    org.bukkit.inventory.InventoryType getType();
    String getTitle();
    void close();
    int countSlots();
    org.bukkit.inventory.ItemStack getItem(int slot);
    void setItem(int slot, org.bukkit.inventory.ItemStack item);
    int convertSlot(int rawSlot);
    org.bukkit.inventory.ItemStack getCursor();
    void setCursor(org.bukkit.inventory.ItemStack item);
    org.bukkit.event.inventory.InventoryType.SlotType getSlotType(int slot);
}