package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerStopSpectatingEntityEvent extends org.bukkit.event.player.PlayerEvent {
    private final Entity spectatedEntity;
    public PlayerStopSpectatingEntityEvent(Player player, Entity spectatedEntity) {
        super(player);
        this.spectatedEntity = spectatedEntity;
    }
    public Entity getSpectatedEntity() { return spectatedEntity; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
