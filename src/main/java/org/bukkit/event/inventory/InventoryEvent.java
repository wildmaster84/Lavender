package org.bukkit.event.inventory;

import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

public class InventoryEvent extends Event {
    protected Inventory inventory;
    public InventoryEvent(Inventory inventory) { this.inventory = inventory; }
    public Inventory getInventory() { return inventory; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
