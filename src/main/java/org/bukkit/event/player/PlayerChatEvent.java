package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerChatEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private String message;
    public PlayerChatEvent(Player player, String message) { super(player); this.message = message; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}