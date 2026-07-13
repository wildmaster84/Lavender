package org.bukkit.event.inventory;

import org.bukkit.inventory.Inventory;

public class InventoryMoveItemEvent extends InventoryEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public InventoryMoveItemEvent(Inventory inventory) { super(inventory); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}