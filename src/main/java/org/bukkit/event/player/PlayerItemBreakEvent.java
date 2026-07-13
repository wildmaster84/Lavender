package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerItemBreakEvent extends PlayerEvent {
    private final ItemStack brokenItem;
    public PlayerItemBreakEvent(Player player, ItemStack brokenItem) {
        super(player);
        this.brokenItem = brokenItem;
    }
    public ItemStack getBrokenItem() { return brokenItem; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
