package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerHarvestBlockEvent extends PlayerEvent {
    public PlayerHarvestBlockEvent(Player player) { super(player); }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}