package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerStopSpectatingEntityEvent extends org.bukkit.event.player.PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity spectatedEntity;
    public PlayerStopSpectatingEntityEvent(Player player, Entity spectatedEntity) {
        super(player);
        this.spectatedEntity = spectatedEntity;
    }
    public Entity getSpectatedEntity() { return spectatedEntity; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
