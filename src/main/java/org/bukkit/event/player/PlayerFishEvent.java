package org.bukkit.event.player;

import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerFishEvent extends PlayerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final FishHook hook;

    public enum State {
        FISHING,
        CAUGHT_FISH,
        CAUGHT_ENTITY,
        IN_GROUND,
        FAILED_ATTEMPT,
        BITE,
        REEL_IN
    }

    public PlayerFishEvent(Player player) { super(player); this.hook = null; }
    public PlayerFishEvent(Player player, FishHook hook) { super(player); this.hook = hook; }
    public FishHook getHook() { return hook; }
    public State getState() { return State.FISHING; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
