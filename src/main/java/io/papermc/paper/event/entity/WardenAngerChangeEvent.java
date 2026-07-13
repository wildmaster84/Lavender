package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WardenAngerChangeEvent extends Event {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity warden;
    private final Entity target;
    private final int oldAngerLevel;
    private final int newAngerLevel;
    public WardenAngerChangeEvent(Entity warden, Entity target, int oldAngerLevel, int newAngerLevel) {
        this.warden = warden;
        this.target = target;
        this.oldAngerLevel = oldAngerLevel;
        this.newAngerLevel = newAngerLevel;
    }
    public Entity getWarden() { return warden; }
    public Entity getTarget() { return target; }
    public int getOldAngerLevel() { return oldAngerLevel; }
    public int getNewAngerLevel() { return newAngerLevel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
