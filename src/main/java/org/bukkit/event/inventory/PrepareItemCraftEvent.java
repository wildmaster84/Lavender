package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class PrepareItemCraftEvent extends org.bukkit.event.Event {
    private static final HandlerList handlers = new HandlerList();
    private final Inventory inventory;
    private final InventoryView view;

    public PrepareItemCraftEvent(Inventory inventory, InventoryView view) {
        this.inventory = inventory;
        this.view = view;
    }

    public Inventory getInventory() { return inventory; }
    public InventoryView getView() { return view; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
