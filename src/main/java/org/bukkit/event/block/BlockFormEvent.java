package org.bukkit.event.block;

import org.bukkit.block.Block;

public class BlockFormEvent extends BlockEvent {
    public BlockFormEvent(Block block) { super(block); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}