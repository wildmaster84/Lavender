package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class EntityJumpEvent extends org.bukkit.event.entity.EntityEvent {
    public EntityJumpEvent(Entity entity) { super(entity); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
