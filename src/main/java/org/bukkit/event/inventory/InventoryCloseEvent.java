package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class InventoryCloseEvent extends InventoryEvent {
    private HumanEntity player;
    public InventoryCloseEvent(InventoryView view) {
        super(view.getTopInventory());
        this.player = view.getPlayer();
    }
    public InventoryCloseEvent(Inventory inventory, HumanEntity player) {
        super(inventory);
        this.player = player;
    }
    public HumanEntity getPlayer() { return player; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
