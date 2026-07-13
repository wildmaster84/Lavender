package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class EntityDamageByEntityEvent extends EntityDamageEvent {
    private final Entity damager;
    public EntityDamageByEntityEvent(Entity damager, Entity damagee, DamageCause cause, double damage) {
        super(damagee, cause, damage);
        this.damager = damager;
    }
    public Entity getDamager() { return damager; }
    public Entity getEntity() { return super.getEntity(); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
