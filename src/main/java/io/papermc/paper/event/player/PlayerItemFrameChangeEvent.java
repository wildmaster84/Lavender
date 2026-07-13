package io.papermc.paper.event.player;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemFrameChangeEvent extends PlayerEvent implements Cancellable {

    public enum ItemFrameChangeAction {
        PLACE,
        REMOVE,
        ROTATE
    }

    private final ItemFrame itemFrame;
    private final ItemFrameChangeAction action;
    private ItemStack itemStack;
    private boolean cancelled;

    public PlayerItemFrameChangeEvent(Player player, ItemFrame itemFrame, ItemStack itemStack, ItemFrameChangeAction action) {
        super(player);
        this.itemFrame = itemFrame;
        this.itemStack = itemStack;
        this.action = action;
    }

    public ItemFrame getItemFrame() { return itemFrame; }
    public ItemFrameChangeAction getAction() { return action; }
    public ItemStack getItemStack() { return itemStack; }
    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }

    @Override
    public boolean isCancelled() { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
