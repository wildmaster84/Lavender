package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.HandlerList;

public class BlockDispenseArmorEvent extends BlockDispenseEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final LivingEntity targetEntity;

    public BlockDispenseArmorEvent(Block block, ItemStack item, LivingEntity target) {
        super(block);
        this.targetEntity = target;
    }

    public LivingEntity getTargetEntity() { return targetEntity; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
