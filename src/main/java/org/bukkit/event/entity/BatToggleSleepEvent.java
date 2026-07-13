package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class BatToggleSleepEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final boolean sleeping;
    public BatToggleSleepEvent(Entity bat, boolean sleeping) {
        super(bat);
        this.sleeping = sleeping;
    }
    public boolean isSleeping() { return sleeping; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
