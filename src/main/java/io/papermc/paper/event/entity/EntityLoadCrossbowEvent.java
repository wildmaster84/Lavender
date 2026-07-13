package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class EntityLoadCrossbowEvent extends Event {
    private final Entity entity;
    private final ItemStack crossbow;
    public EntityLoadCrossbowEvent(Entity entity, ItemStack crossbow) {
        this.entity = entity;
        this.crossbow = crossbow;
    }
    public Entity getEntity() { return entity; }
    public ItemStack getCrossbow() { return crossbow; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
