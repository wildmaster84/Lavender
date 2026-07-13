package org.bukkit.event.block;

import org.bukkit.block.Block;

public class BlockRedstoneEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public BlockRedstoneEvent(Block block, int oldCurrent, int newCurrent) { super(block); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}