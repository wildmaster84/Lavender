package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerInventorySlotChangeEvent extends org.bukkit.event.player.PlayerEvent {
    private final int slot;
    private final ItemStack oldItem;
    private final ItemStack newItem;
    public PlayerInventorySlotChangeEvent(Player player, int slot, ItemStack oldItem, ItemStack newItem) {
        super(player);
        this.slot = slot;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }
    public int getSlot() { return slot; }
    public ItemStack getOldItem() { return oldItem; }
    public ItemStack getNewItem() { return newItem; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
