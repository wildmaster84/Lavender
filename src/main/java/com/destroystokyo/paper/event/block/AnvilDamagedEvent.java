package com.destroystokyo.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class AnvilDamagedEvent extends org.bukkit.event.block.BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public AnvilDamagedEvent(Block block) { super(block); }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
