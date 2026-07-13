package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import java.util.List;

public class SpongeAbsorbEvent extends BlockEvent {
    private final List<Block> blocks;
    public SpongeAbsorbEvent(Block block, List<Block> blocks) {
        super(block);
        this.blocks = blocks;
    }
    public List<Block> getBlocks() { return blocks; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
