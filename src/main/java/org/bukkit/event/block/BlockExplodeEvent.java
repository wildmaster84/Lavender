package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import java.util.List;

public class BlockExplodeEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final List<Block> blocks;
    public BlockExplodeEvent(Block block, List<Block> blocks) {
        super(block);
        this.blocks = blocks;
    }
    public List<Block> getBlocks() { return blocks; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
