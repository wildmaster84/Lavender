package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityPotionEffectEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public EntityPotionEffectEvent(Entity entity) { super(entity); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }

    public enum Cause {
        POTION_SPLASH,
        POTION_DRINK,
        ARROW,
        BEACON,
        ATTACK,
        COMMAND,
        CONDUIT,
        WITHER_ROSE,
        TOTEM,
        DOLPHIN_GRACE,
        UNKNOWN,
        AREA_EFFECT_CLOUD,
        SPIDER_SPAWN,
        PLUGIN
    }
}