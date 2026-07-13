package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class BlockBreakEvent extends BlockEvent implements Cancellable {
    private boolean cancelled = false;
    private final Player player;
    private int expToDrop = 0;

    public BlockBreakEvent(Block block, Player player) { super(block); this.player = player; }
    public Player getPlayer() { return player; }
    public int getExpToDrop() { return expToDrop; }
    public void setExpToDrop(int exp) { this.expToDrop = exp; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
