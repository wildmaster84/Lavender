package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerBedLeaveEvent extends PlayerEvent {
    public PlayerBedLeaveEvent(Player player) { super(player); }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}