package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerEditBookEvent extends PlayerEvent {
    private final ItemStack book;
    public PlayerEditBookEvent(Player player, ItemStack book) {
        super(player);
        this.book = book;
    }
    public ItemStack getBook() { return book; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
