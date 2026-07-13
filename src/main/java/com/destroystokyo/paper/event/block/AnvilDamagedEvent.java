package com.destroystokyo.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class AnvilDamagedEvent extends org.bukkit.event.block.BlockEvent {
    public AnvilDamagedEvent(Block block) { super(block); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
