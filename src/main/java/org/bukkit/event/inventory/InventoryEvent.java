package org.bukkit.event.inventory;

import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

public class InventoryEvent extends Event {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    protected Inventory inventory;
    public InventoryEvent(Inventory inventory) { this.inventory = inventory; }
    public Inventory getInventory() { return inventory; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
