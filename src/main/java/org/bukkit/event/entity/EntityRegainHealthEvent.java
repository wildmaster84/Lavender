package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityRegainHealthEvent extends EntityEvent {
    public EntityRegainHealthEvent(Entity entity, double amount) { super(entity); }
    public double getAmount() { return 0; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}