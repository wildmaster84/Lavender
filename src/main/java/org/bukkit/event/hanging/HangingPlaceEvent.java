package org.bukkit.event.hanging;

import org.bukkit.entity.HangingEntity;

public class HangingPlaceEvent extends HangingEvent {
    public HangingPlaceEvent(HangingEntity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}