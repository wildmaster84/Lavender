package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.Location;

public class EntityPortalEvent extends EntityTeleportEvent {
    public EntityPortalEvent(Entity entity, Location from, Location to) { super(entity, from, to); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}