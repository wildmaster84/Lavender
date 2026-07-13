package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class FluidLevelChangeEvent extends BlockEvent {
    public FluidLevelChangeEvent(Block block) { super(block); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
