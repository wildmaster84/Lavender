package org.bukkit.event.block;

import org.bukkit.block.Block;

public class BlockFromToEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public BlockFromToEvent(Block block, Block to) { super(block); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}