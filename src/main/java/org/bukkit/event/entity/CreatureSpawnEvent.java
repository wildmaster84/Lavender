package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;

public class CreatureSpawnEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public CreatureSpawnEvent(LivingEntity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }

    public enum SpawnReason {
        CUSTOM
    }
}