package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class FoodLevelChangeEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public FoodLevelChangeEvent(Entity entity) { super(entity); }
    public int getFoodLevel() { return 20; }
    public void setFoodLevel(int level) {}
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}