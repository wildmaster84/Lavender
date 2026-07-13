package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.block.Block;

public class EntityDamageByBlockEvent extends EntityDamageEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Block damager;
    public EntityDamageByBlockEvent(Block damager, Entity entity, DamageCause cause, double damage) {
        super(entity, cause, damage);
        this.damager = damager;
    }
    public Block getDamager() { return damager; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
