package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class EntityDeathEvent extends EntityEvent {
    public EntityDeathEvent(Entity entity, List<ItemStack> drops, int droppedExp) { super(entity); }
    public List<ItemStack> getDrops() { return new java.util.ArrayList<>(); }
    public int getDroppedExp() { return 0; }
    public void setDroppedExp(int exp) {}
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}