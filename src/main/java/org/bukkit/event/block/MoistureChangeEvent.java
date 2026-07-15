package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class MoistureChangeEvent extends BlockEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private boolean cancelled;

    public MoistureChangeEvent(Block block) {
        super(block);
    }

    @Override
    public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }

    public boolean isCancelled() { return cancelled; }

    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
}
