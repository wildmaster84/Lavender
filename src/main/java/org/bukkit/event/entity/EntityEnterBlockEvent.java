package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class EntityEnterBlockEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Block block;
    public EntityEnterBlockEvent(Entity entity, Block block) {
        super(entity);
        this.block = block;
    }
    public Block getBlock() { return block; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
