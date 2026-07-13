package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

public abstract class EntityEvent extends Event {
    protected Entity entity;
    public EntityEvent(Entity entity) { this.entity = entity; }
    public Entity getEntity() { return entity; }
}
