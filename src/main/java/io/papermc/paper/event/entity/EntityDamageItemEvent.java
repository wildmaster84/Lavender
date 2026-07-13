package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class EntityDamageItemEvent extends Event {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity entity;
    private final ItemStack item;
    private final int damage;
    public EntityDamageItemEvent(Entity entity, ItemStack item, int damage) {
        this.entity = entity;
        this.item = item;
        this.damage = damage;
    }
    public Entity getEntity() { return entity; }
    public ItemStack getItem() { return item; }
    public int getDamage() { return damage; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
