package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerJoinEvent extends PlayerEvent {
    private String joinMessage;
    public PlayerJoinEvent(Player player, String joinMessage) { super(player); this.joinMessage = joinMessage; }
    public String getJoinMessage() { return joinMessage; }
    public void setJoinMessage(String message) { this.joinMessage = message; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}