package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.GameMode;

public class PlayerGameModeChangeEvent extends PlayerEvent {
    private GameMode newGameMode;
    public PlayerGameModeChangeEvent(Player player, GameMode newGameMode) { super(player); this.newGameMode = newGameMode; }
    public GameMode getNewGameMode() { return newGameMode; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}