package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class FoodLevelChangeEvent extends EntityEvent {
    public FoodLevelChangeEvent(Entity entity) { super(entity); }
    public int getFoodLevel() { return 20; }
    public void setFoodLevel(int level) {}
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}