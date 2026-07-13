package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.GameMode;

public class PlayerGameModeChangeEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private GameMode newGameMode;
    public PlayerGameModeChangeEvent(Player player, GameMode newGameMode) { super(player); this.newGameMode = newGameMode; }
    public GameMode getNewGameMode() { return newGameMode; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}