package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class InventoryCloseEvent extends InventoryEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
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
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
