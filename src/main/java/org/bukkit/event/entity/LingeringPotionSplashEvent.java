package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class LingeringPotionSplashEvent extends EntityEvent {
    public LingeringPotionSplashEvent(Entity entity) { super(entity); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
