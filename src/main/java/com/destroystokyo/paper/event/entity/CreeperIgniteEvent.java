package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CreeperIgniteEvent extends org.bukkit.event.entity.EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public CreeperIgniteEvent(Entity entity) { super(entity); }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
