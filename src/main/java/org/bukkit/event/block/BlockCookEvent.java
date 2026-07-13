package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BlockCookEvent extends BlockEvent {
    private final ItemStack source;
    private final ItemStack result;
    public BlockCookEvent(Block block, ItemStack source, ItemStack result) {
        super(block);
        this.source = source;
        this.result = result;
    }
    public ItemStack getSource() { return source; }
    public ItemStack getResult() { return result; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
