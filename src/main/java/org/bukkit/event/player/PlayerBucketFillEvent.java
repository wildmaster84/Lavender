package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerBucketFillEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public PlayerBucketFillEvent(Player player) { super(player); }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}