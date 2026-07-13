package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityInsideBlockEvent extends Event {
    private final Entity entity;
    public EntityInsideBlockEvent(Entity entity) { this.entity = entity; }
    public Entity getEntity() { return entity; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
