package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerItemDamageEvent extends PlayerEvent {
    private final ItemStack item;
    private final int damage;
    public PlayerItemDamageEvent(Player player, ItemStack item, int damage) {
        super(player);
        this.item = item;
        this.damage = damage;
    }
    public ItemStack getItem() { return item; }
    public int getDamage() { return damage; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
