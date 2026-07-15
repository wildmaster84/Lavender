package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class EntityBlockFormEvent extends BlockFormEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Entity entity;

    public EntityBlockFormEvent(Entity entity, Block block, org.bukkit.block.BlockState blockState) {
        super(block);
        this.entity = entity;
    }

    public Entity getEntity() { return entity; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
