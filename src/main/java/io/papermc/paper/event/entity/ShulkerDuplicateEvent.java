package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ShulkerDuplicateEvent extends Event {
    private final Entity shulker;
    private final Entity clone;
    public ShulkerDuplicateEvent(Entity shulker, Entity clone) {
        this.shulker = shulker;
        this.clone = clone;
    }
    public Entity getShulker() { return shulker; }
    public Entity getClone() { return clone; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
