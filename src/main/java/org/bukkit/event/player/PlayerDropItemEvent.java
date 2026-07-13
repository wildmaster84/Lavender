package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerDropItemEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public PlayerDropItemEvent(Player player) { super(player); }
    public boolean isCancelled() { return false; }
    public void setCancelled(boolean cancel) {}
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}