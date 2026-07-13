package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;

public class PlayerMoveEvent extends PlayerEvent implements Cancellable {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private boolean cancelled = false;
    private Location from;
    private Location to;
    public PlayerMoveEvent(Player player, Location from, Location to) { super(player); this.from = from; this.to = to; }
    public Location getFrom() { return from; }
    public void setFrom(Location from) { this.from = from; }
    public Location getTo() { return to; }
    public void setTo(Location to) { this.to = to; }
    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
