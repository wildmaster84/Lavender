package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityCombustByBlockEvent extends EntityCombustEvent {
    public EntityCombustByBlockEvent(Entity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}