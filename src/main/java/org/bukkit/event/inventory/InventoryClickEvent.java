package org.bukkit.event.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;

public class InventoryClickEvent extends InventoryEvent implements org.bukkit.event.Cancellable {
    private Inventory clickedInventory;
    private InventoryView view;
    private InventoryAction action;
    private ClickType clickType;
    private HumanEntity whoClicked;
    private int slot;
    private int rawSlot;
    private ItemStack currentItem;
    private ItemStack cursor;
    private boolean cancelled;

    public InventoryClickEvent(InventoryView view, InventoryAction action, ClickType clickType, int slot, int rawSlot) {
        super(view.getTopInventory());
        this.view = view;
        this.action = action;
        this.clickType = clickType;
        this.slot = slot;
        this.rawSlot = rawSlot;
        this.whoClicked = view.getPlayer();
        this.currentItem = view.getItem(rawSlot);
        this.cursor = view.getCursor();
    }

    public InventoryClickEvent(InventoryView view, InventoryAction action, ClickType clickType, int slot, int rawSlot, ItemStack currentItem) {
        super(view.getTopInventory());
        this.view = view;
        this.action = action;
        this.clickType = clickType;
        this.slot = slot;
        this.rawSlot = rawSlot;
        this.currentItem = currentItem;
        this.cursor = view.getCursor();
        this.whoClicked = view.getPlayer();
    }

    public Inventory getClickedInventory() { return clickedInventory; }
    public void setClickedInventory(Inventory inv) { this.clickedInventory = inv; }
    public InventoryAction getAction() { return action; }
    public ClickType getClick() { return clickType; }
    public HumanEntity getWhoClicked() { return whoClicked; }
    public int getSlot() { return slot; }
    public int getRawSlot() { return rawSlot; }
    public org.bukkit.event.inventory.InventoryType.SlotType getSlotType() { return view.getSlotType(rawSlot); }
    public ItemStack getCurrentItem() { return currentItem; }
    public void setCurrentItem(ItemStack item) { this.currentItem = item; }
    public ItemStack getCursor() { return cursor; }
    public void setCursor(ItemStack item) { this.cursor = item; }
    public InventoryView getView() { return view; }
    public boolean isShiftClick() { return clickType != null && clickType.isShiftClick(); }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
