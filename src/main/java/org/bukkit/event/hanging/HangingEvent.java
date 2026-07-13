package org.bukkit.event.hanging;

import org.bukkit.entity.HangingEntity;
import org.bukkit.event.Event;

public abstract class HangingEvent extends Event {
    protected HangingEntity entity;
    public HangingEvent(HangingEntity entity) { this.entity = entity; }
    public HangingEntity getEntity() { return entity; }
}