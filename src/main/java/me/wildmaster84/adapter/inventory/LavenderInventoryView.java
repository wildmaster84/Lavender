package me.wildmaster84.adapter.inventory;

import me.wildmaster84.adapter.player.LavenderPlayer;
import me.wildmaster84.adapter.player.LavenderPlayerInventory;

public class LavenderInventoryView implements org.bukkit.inventory.InventoryView {

    private final LavenderPlayer player;
    private final LavenderInventory topInventory;
    private final org.bukkit.inventory.InventoryType type;

    public LavenderInventoryView(LavenderPlayer player, LavenderInventory topInventory, org.bukkit.inventory.InventoryType type) {
        this.player = player;
        this.topInventory = topInventory;
        this.type = type;
    }

    @Override
    public org.bukkit.entity.HumanEntity getPlayer() { return player; }

    @Override
    public org.bukkit.inventory.Inventory getTopInventory() { return topInventory; }

    @Override
    public org.bukkit.inventory.Inventory getBottomInventory() { return player.getInventory(); }

    @Override
    public org.bukkit.inventory.InventoryType getType() { return type; }

    @Override
    public String getTitle() {
        if (topInventory != null) return topInventory.getTitle();
        return "";
    }

    @Override
    public void close() { player.closeInventory(); }

    @Override
    public int countSlots() {
        return topInventory.getSize() + 36;
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int slot) {
        int topSize = topInventory.getSize();
        if (slot >= 0 && slot < topSize) {
            return topInventory.getItem(slot);
        } else if (slot >= topSize && slot < topSize + 36) {
            return ((LavenderPlayerInventory) player.getInventory()).getItem(slot - topSize);
        }
        return new org.bukkit.inventory.ItemStack();
    }

    @Override
    public void setItem(int slot, org.bukkit.inventory.ItemStack item) {
        int topSize = topInventory.getSize();
        if (slot >= 0 && slot < topSize) {
            topInventory.setItem(slot, item);
        } else if (slot >= topSize && slot < topSize + 36) {
            ((LavenderPlayerInventory) player.getInventory()).setItem(slot - topSize, item);
        }
    }

    @Override
    public int convertSlot(int rawSlot) {
        int topSize = topInventory.getSize();
        if (rawSlot >= 0 && rawSlot < topSize) {
            return rawSlot;
        }
        int playerSlot = rawSlot - topSize;
        if (playerSlot >= 27) playerSlot -= 27;
        return playerSlot;
    }

    @Override
    public org.bukkit.inventory.ItemStack getCursor() {
        return new org.bukkit.inventory.ItemStack();
    }

    @Override
    public void setCursor(org.bukkit.inventory.ItemStack item) {}

    @Override
    public org.bukkit.event.inventory.InventoryType.SlotType getSlotType(int slot) {
        int topSize = topInventory.getSize();
        if (slot >= 0 && slot < topSize) {
            return org.bukkit.event.inventory.InventoryType.SlotType.CONTAINER;
        }
        return org.bukkit.event.inventory.InventoryType.SlotType.QUICKBAR;
    }
}
