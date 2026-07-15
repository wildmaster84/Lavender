package org.bukkit.event.world;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class StructureGrowEvent extends WorldEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Location location;
    private final TreeType treeType;
    private final boolean fromBonemeal;
    private final Player player;
    private final List<BlockState> blocks;
    private boolean cancelled;

    public StructureGrowEvent(Location location, TreeType treeType, boolean fromBonemeal, Player player, List<BlockState> blocks) {
        super(location != null ? location.getWorld() : null);
        this.location = location;
        this.treeType = treeType;
        this.fromBonemeal = fromBonemeal;
        this.player = player;
        this.blocks = blocks;
    }

    public Location getLocation() { return location; }
    public TreeType getTreeType() { return treeType; }
    public boolean isFromBonemeal() { return fromBonemeal; }
    public Player getPlayer() { return player; }
    public List<BlockState> getBlocks() { return blocks; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
