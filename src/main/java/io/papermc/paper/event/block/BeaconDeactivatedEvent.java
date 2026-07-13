package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class BeaconDeactivatedEvent extends org.bukkit.event.block.BlockEvent {
    public BeaconDeactivatedEvent(Block block) { super(block); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
