package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;

public class PlayerTeleportEvent extends PlayerMoveEvent {
    public PlayerTeleportEvent(Player player, Location from, Location to) { super(player, from, to); }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}