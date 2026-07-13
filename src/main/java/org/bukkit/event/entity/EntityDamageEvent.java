package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class EntityDamageEvent extends EntityEvent implements Cancellable {
    private boolean cancelled = false;
    private double damage;
    private final DamageCause cause;

    public enum DamageCause {
        CONTACT, ENTITY_ATTACK, PROJECTILE, SUFFOCATION, FALL, FIRE, FIRE_TICK,
        MELTING, LAVA, DROWNING, BLOCK_EXPLOSION, ENTITY_EXPLOSION, VOID,
        LIGHTNING, SUICIDE, STARVATION, POISON, MAGIC, WITHER, FALLING_BLOCK,
        THORNS, DRAGON_BREATH, CUSTOM, FLY_INTO_WALL, HOT_FLOOR, CRAMMING, DRYOUT,
        FREEZE, KILL, SONIC_BOOM, WORLD_BORDER
    }

    public EntityDamageEvent(Entity entity, DamageCause cause, double damage) {
        super(entity);
        this.cause = cause;
        this.damage = damage;
    }

    public double getDamage() { return damage; }
    public void setDamage(double damage) { this.damage = damage; }
    public double getFinalDamage() { return damage; }
    public DamageCause getCause() { return cause; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
