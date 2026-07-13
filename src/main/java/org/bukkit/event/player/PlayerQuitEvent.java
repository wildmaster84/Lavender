package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerQuitEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private String quitMessage;
    public PlayerQuitEvent(Player player, String quitMessage) { super(player); this.quitMessage = quitMessage; }
    public String getQuitMessage() { return quitMessage; }
    public void setQuitMessage(String message) { this.quitMessage = message; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}