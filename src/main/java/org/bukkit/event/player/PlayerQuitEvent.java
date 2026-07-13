package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerQuitEvent extends PlayerEvent {
    private String quitMessage;
    public PlayerQuitEvent(Player player, String quitMessage) { super(player); this.quitMessage = quitMessage; }
    public String getQuitMessage() { return quitMessage; }
    public void setQuitMessage(String message) { this.quitMessage = message; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}