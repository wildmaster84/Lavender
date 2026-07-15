package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerUnleashEntityEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Entity entity;
    private boolean cancelled = false;

    public PlayerUnleashEntityEvent(Player player, Entity entity) {
        super(player);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
