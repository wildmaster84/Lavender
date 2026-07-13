package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityRegainHealthEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public EntityRegainHealthEvent(Entity entity, double amount) { super(entity); }
    public double getAmount() { return 0; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}