package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityDamageByEntityEvent extends EntityDamageEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity damager;
    public EntityDamageByEntityEvent(Entity damager, Entity damagee, DamageCause cause, double damage) {
        super(damagee, cause, damage);
        this.damager = damager;
    }
    public Entity getDamager() { return damager; }
    public Entity getEntity() { return super.getEntity(); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
