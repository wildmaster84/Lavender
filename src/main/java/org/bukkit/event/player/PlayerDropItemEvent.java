package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerDropItemEvent extends PlayerEvent {
    public PlayerDropItemEvent(Player player) { super(player); }
    public boolean isCancelled() { return false; }
    public void setCancelled(boolean cancel) {}
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}