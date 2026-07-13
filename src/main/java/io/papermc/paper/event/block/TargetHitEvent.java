package io.papermc.paper.event.block;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TargetHitEvent extends Event {
    private final Entity entity;
    private final int signalStrength;
    public TargetHitEvent(Entity entity, int signalStrength) {
        this.entity = entity;
        this.signalStrength = signalStrength;
    }
    public Entity getEntity() { return entity; }
    public int getSignalStrength() { return signalStrength; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
