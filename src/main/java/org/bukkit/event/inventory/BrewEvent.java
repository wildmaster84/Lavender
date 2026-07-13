package org.bukkit.event.inventory;

import org.bukkit.inventory.Inventory;

public class BrewEvent extends InventoryEvent {
    public BrewEvent(Inventory inventory) { super(inventory); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}