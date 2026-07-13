package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockDamageEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public BlockDamageEvent(Block block, Player player) { super(block); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}