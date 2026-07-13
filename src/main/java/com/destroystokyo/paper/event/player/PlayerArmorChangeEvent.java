package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerArmorChangeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack oldItem;
    private final ItemStack newItem;
    private final SlotType slotType;

    public enum SlotType {
        HEAD,
        CHEST,
        LEGS,
        FEET
    }

    public PlayerArmorChangeEvent(Player player, ItemStack oldItem, ItemStack newItem, SlotType slotType) {
        super(player);
        this.oldItem = oldItem;
        this.newItem = newItem;
        this.slotType = slotType;
    }

    public ItemStack getOldItem() { return oldItem; }
    public ItemStack getNewItem() { return newItem; }
    public SlotType getSlotType() { return slotType; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
