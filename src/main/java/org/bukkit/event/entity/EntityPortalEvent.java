package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.Location;

public class EntityPortalEvent extends EntityTeleportEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public EntityPortalEvent(Entity entity, Location from, Location to) { super(entity, from, to); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}