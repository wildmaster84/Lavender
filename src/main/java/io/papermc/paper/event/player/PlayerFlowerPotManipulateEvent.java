package io.papermc.paper.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFlowerPotManipulateEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block flowerpot;
    private final ItemStack item;
    private final boolean placing;
    private boolean cancelled = false;

    public PlayerFlowerPotManipulateEvent(Player player, Block flowerpot, ItemStack item, boolean placing) {
        super(player);
        this.flowerpot = flowerpot;
        this.item = item;
        this.placing = placing;
    }

    public Block getFlowerpot() { return flowerpot; }
    public ItemStack getItem() { return item; }
    public boolean isPlacing() { return placing; }

    @Override
    public boolean isCancelled() { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
