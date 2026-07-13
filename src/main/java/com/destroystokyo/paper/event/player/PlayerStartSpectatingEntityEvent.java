package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerStartSpectatingEntityEvent extends org.bukkit.event.player.PlayerEvent {
    private final Entity currentSpectatorTarget;
    private final Entity newSpectatorTarget;
    public PlayerStartSpectatingEntityEvent(Player player, Entity currentSpectatorTarget, Entity newSpectatorTarget) {
        super(player);
        this.currentSpectatorTarget = currentSpectatorTarget;
        this.newSpectatorTarget = newSpectatorTarget;
    }
    public Entity getCurrentSpectatorTarget() { return currentSpectatorTarget; }
    public Entity getNewSpectatorTarget() { return newSpectatorTarget; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
