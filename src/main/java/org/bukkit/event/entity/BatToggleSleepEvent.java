package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class BatToggleSleepEvent extends EntityEvent {
    private final boolean sleeping;
    public BatToggleSleepEvent(Entity bat, boolean sleeping) {
        super(bat);
        this.sleeping = sleeping;
    }
    public boolean isSleeping() { return sleeping; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
