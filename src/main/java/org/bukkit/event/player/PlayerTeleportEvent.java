package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;

public class PlayerTeleportEvent extends PlayerMoveEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public PlayerTeleportEvent(Player player, Location from, Location to) { super(player, from, to); }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}