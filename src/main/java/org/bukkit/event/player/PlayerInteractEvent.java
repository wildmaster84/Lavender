package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEvent extends PlayerEvent implements org.bukkit.event.Cancellable {

    private final Action action;
    private final ItemStack item;
    private final Block clickedBlock;
    private final BlockFace blockFace;
    private final EquipmentSlot hand;
    private boolean cancelled = false;

    private static final org.bukkit.event.HandlerList handlers = new org.bukkit.event.HandlerList();

    public PlayerInteractEvent(Player player, Action action, ItemStack item, Block clickedBlock, BlockFace blockFace) {
        super(player);
        this.action = action;
        this.item = item;
        this.clickedBlock = clickedBlock;
        this.blockFace = blockFace;
        this.hand = EquipmentSlot.HAND;
    }

    public PlayerInteractEvent(Player player, Action action, ItemStack item, Block clickedBlock, BlockFace blockFace, EquipmentSlot hand) {
        super(player);
        this.action = action;
        this.item = item;
        this.clickedBlock = clickedBlock;
        this.blockFace = blockFace;
        this.hand = hand;
    }

    public Action getAction() { return action; }
    public ItemStack getItem() { return item; }
    public Block getClickedBlock() { return clickedBlock; }
    public BlockFace getBlockFace() { return blockFace; }
    public EquipmentSlot getHand() { return hand; }

    public org.bukkit.event.Event.Result useItemInHand() {
        return org.bukkit.event.Event.Result.DEFAULT;
    }

    public org.bukkit.event.Event.Result useInteractedBlock() {
        return cancelled ? org.bukkit.event.Event.Result.DENY : org.bukkit.event.Event.Result.ALLOW;
    }

    @Override
    public boolean isCancelled() { return cancelled; }

    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public org.bukkit.event.HandlerList getHandlers() { return handlers; }
    public static org.bukkit.event.HandlerList getHandlerList() { return handlers; }
}
