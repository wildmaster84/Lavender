package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerTakeLecternBookEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Block block;
    private final ItemStack book;
    private boolean cancelled;

    public PlayerTakeLecternBookEvent(Player player, Block block, ItemStack book) {
        super(player);
        this.block = block;
        this.book = book;
    }

    public Block getBlock() { return block; }
    public ItemStack getBook() { return book; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
