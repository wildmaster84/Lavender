package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class EntityEnterBlockEvent extends EntityEvent {
    private final Block block;
    public EntityEnterBlockEvent(Entity entity, Block block) {
        super(entity);
        this.block = block;
    }
    public Block getBlock() { return block; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
