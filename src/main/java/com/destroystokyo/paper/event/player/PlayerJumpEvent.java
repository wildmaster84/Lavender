package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerJumpEvent extends org.bukkit.event.player.PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public PlayerJumpEvent(Player player) { super(player); }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
