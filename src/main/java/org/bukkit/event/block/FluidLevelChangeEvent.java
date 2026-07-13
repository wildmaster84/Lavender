package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class FluidLevelChangeEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public FluidLevelChangeEvent(Block block) { super(block); }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
