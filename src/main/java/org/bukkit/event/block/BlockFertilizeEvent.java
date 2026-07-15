package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class BlockFertilizeEvent extends BlockEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private boolean cancelled;

    public BlockFertilizeEvent(Block block, Player player) {
        super(block);
        this.player = player;
    }

    public Player getPlayer() { return player; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
