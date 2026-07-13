package org.bukkit.event.player;

import org.bukkit.entity.Player;

public class PlayerLoginEvent extends PlayerEvent {

    public enum Result {
        ALLOWED,
        DENIED,
        KICK_WHITELIST,
        KICK_BANNED,
        KICK_FULL,
        KICK_OTHER
    }

    private final Result result;

    public PlayerLoginEvent(Player player) {
        super(player);
        this.result = Result.ALLOWED;
    }

    public PlayerLoginEvent(Player player, Result result) {
        super(player);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public org.bukkit.event.HandlerList getHandlers() {
        return new org.bukkit.event.HandlerList();
    }

    public static org.bukkit.event.HandlerList getHandlerList() {
        return new org.bukkit.event.HandlerList();
    }
}
