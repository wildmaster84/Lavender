package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockPlaceEvent extends BlockEvent {
    public BlockPlaceEvent(Block block, org.bukkit.block.BlockState replacedBlockState, Block placedAgainst, org.bukkit.inventory.ItemStack itemInHand, Player player, boolean canBuild) { super(block); }
    public Player getPlayer() { return null; }
    public boolean canBuild() { return true; }
    public boolean isCancelled() { return false; }
    public void setCancelled(boolean cancel) {}
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}