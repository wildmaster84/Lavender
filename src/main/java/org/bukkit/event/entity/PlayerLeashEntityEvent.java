package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerLeashEntityEvent extends EntityEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private boolean cancelled;

    public PlayerLeashEntityEvent(Entity entity, Player player) {
        super(entity);
        this.player = player;
    }

    public Player getPlayer() { return player; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
