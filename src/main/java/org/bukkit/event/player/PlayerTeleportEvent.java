package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.Location;

public class PlayerTeleportEvent extends PlayerMoveEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();

    public enum TeleportCause {
        END_PORTAL,
        NETHER_PORTAL,
        COMMAND,
        DISMOUNT,
        ENDER_PEARL,
        END_GATEWAY,
        CHORUS_FRUIT,
        PLUGIN,
        SPECTATE,
        UNKNOWN,
        NETHER_PORTAL_AWAIT
    }

    public PlayerTeleportEvent(Player player, Location from, Location to) { super(player, from, to); }
    public TeleportCause getCause() { return TeleportCause.UNKNOWN; }
    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}