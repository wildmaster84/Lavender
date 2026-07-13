package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerJoinEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private String joinMessage;
    public PlayerJoinEvent(Player player, String joinMessage) { super(player); this.joinMessage = joinMessage; }
    public String getJoinMessage() { return joinMessage; }
    public void setJoinMessage(String message) { this.joinMessage = message; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}