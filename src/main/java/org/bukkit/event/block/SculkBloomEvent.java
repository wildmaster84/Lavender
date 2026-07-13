package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class SculkBloomEvent extends BlockEvent {
    private final int charge;
    public SculkBloomEvent(Block block, int charge) {
        super(block);
        this.charge = charge;
    }
    public int getCharge() { return charge; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
