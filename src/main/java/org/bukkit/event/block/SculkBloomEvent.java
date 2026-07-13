package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class SculkBloomEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final int charge;
    public SculkBloomEvent(Block block, int charge) {
        super(block);
        this.charge = charge;
    }
    public int getCharge() { return charge; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
