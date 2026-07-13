package me.wildmaster84.adapter.inventory;

import me.wildmaster84.adapter.player.LavenderPlayer;
import me.wildmaster84.adapter.player.LavenderPlayerInventory;

public class PlayerInventoryView implements org.bukkit.inventory.InventoryView {

    private final LavenderPlayer player;

    public PlayerInventoryView(LavenderPlayer player) {
        this.player = player;
    }

    @Override
    public org.bukkit.entity.HumanEntity getPlayer() { return player; }

    @Override
    public org.bukkit.inventory.Inventory getTopInventory() { return player.getInventory(); }

    @Override
    public org.bukkit.inventory.Inventory getBottomInventory() { return player.getInventory(); }

    @Override
    public org.bukkit.inventory.InventoryType getType() { return org.bukkit.inventory.InventoryType.PLAYER; }

    @Override
    public String getTitle() { return "Inventory"; }

    @Override
    public void close() { player.closeInventory(); }

    @Override
    public int countSlots() { return 36; }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int slot) {
        return ((LavenderPlayerInventory) player.getInventory()).getItem(slot);
    }

    @Override
    public void setItem(int slot, org.bukkit.inventory.ItemStack item) {
        ((LavenderPlayerInventory) player.getInventory()).setItem(slot, item);
    }

    @Override
    public int convertSlot(int rawSlot) { return rawSlot; }

    @Override
    public org.bukkit.inventory.ItemStack getCursor() {
        return new org.bukkit.inventory.ItemStack();
    }

    @Override
    public void setCursor(org.bukkit.inventory.ItemStack item) {}

    @Override
    public org.bukkit.event.inventory.InventoryType.SlotType getSlotType(int slot) {
        return org.bukkit.event.inventory.InventoryType.SlotType.QUICKBAR;
    }
}
