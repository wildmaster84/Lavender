package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;

public class EntityKnockbackByEntityEvent extends EntityEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Entity knockedBy;
    private final Vector knockbackFactor;
    private boolean cancelled;

    public EntityKnockbackByEntityEvent(Entity entity, Entity knockedBy, Vector knockbackFactor) {
        super(entity);
        this.knockedBy = knockedBy;
        this.knockbackFactor = knockbackFactor;
    }

    public Entity getKnockedBy() { return knockedBy; }
    public Vector getKnockbackFactor() { return knockbackFactor; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
