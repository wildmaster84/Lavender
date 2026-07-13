package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class InventoryOpenEvent extends InventoryEvent implements org.bukkit.event.Cancellable {
    private HumanEntity player;
    private InventoryView view;
    private boolean cancelled;

    public InventoryOpenEvent(InventoryView view) {
        super(view.getTopInventory());
        this.view = view;
        this.player = view.getPlayer();
    }

    public InventoryOpenEvent(Inventory inventory, HumanEntity player) {
        super(inventory);
        this.player = player;
    }

    public HumanEntity getPlayer() { return player; }
    public InventoryView getView() { return view; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
