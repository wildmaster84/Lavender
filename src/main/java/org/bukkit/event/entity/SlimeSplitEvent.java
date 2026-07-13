package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class SlimeSplitEvent extends EntityEvent {
    public SlimeSplitEvent(Entity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}