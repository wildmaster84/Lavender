package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CauldronLevelChangeEvent extends BlockEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity entity;
    public CauldronLevelChangeEvent(Block block, Entity entity) {
        super(block);
        this.entity = entity;
    }
    public Entity getEntity() { return entity; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
