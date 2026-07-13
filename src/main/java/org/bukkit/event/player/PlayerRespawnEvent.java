package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;

public class PlayerRespawnEvent extends PlayerEvent {
    private Location respawnLocation;
    public PlayerRespawnEvent(Player player, Location respawnLocation) { super(player); this.respawnLocation = respawnLocation; }
    public Location getRespawnLocation() { return respawnLocation; }
    public void setRespawnLocation(Location location) { this.respawnLocation = location; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}