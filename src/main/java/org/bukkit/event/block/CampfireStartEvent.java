package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class CampfireStartEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final ItemStack source;
    private final int cookingTime;
    public CampfireStartEvent(Block block, ItemStack source, int cookingTime) {
        super(block);
        this.source = source;
        this.cookingTime = cookingTime;
    }
    public ItemStack getSource() { return source; }
    public int getCookingTime() { return cookingTime; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
