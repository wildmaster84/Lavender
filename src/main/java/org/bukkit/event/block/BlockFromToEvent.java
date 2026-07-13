package org.bukkit.event.block;

import org.bukkit.block.Block;

public class BlockFromToEvent extends BlockEvent {
    public BlockFromToEvent(Block block, Block to) { super(block); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}