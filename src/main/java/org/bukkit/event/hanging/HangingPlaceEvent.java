package org.bukkit.event.hanging;

import org.bukkit.entity.HangingEntity;

public class HangingPlaceEvent extends HangingEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public HangingPlaceEvent(HangingEntity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}