package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BrewingStartEvent extends BlockEvent {
    private final ItemStack source;
    private final int brewingTime;
    public BrewingStartEvent(Block block, ItemStack source, int brewingTime) {
        super(block);
        this.source = source;
        this.brewingTime = brewingTime;
    }
    public ItemStack getSource() { return source; }
    public int getBrewingTime() { return brewingTime; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
