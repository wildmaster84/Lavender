package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;

public class PlayerRespawnEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private Location respawnLocation;
    public PlayerRespawnEvent(Player player, Location respawnLocation) { super(player); this.respawnLocation = respawnLocation; }
    public Location getRespawnLocation() { return respawnLocation; }
    public void setRespawnLocation(Location location) { this.respawnLocation = location; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}