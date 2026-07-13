package io.papermc.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public class EntityMoveEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Location from;
    private Location to;
    private boolean cancelled = false;

    public EntityMoveEvent(Entity entity, Location from, Location to) {
        super(entity);
        this.from = from;
        this.to = to;
    }

    public Location getFrom() { return from; }
    public Location getTo() { return to; }
    public void setTo(Location to) { this.to = to; }

    @Override
    public boolean isCancelled() { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
