package io.papermc.paper.event.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChunkLoadEvent extends Event {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Player player;
    private final int chunkX;
    private final int chunkZ;
    public PlayerChunkLoadEvent(Player player, int chunkX, int chunkZ) {
        this.player = player;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }
    public Player getPlayer() { return player; }
    public int getChunkX() { return chunkX; }
    public int getChunkZ() { return chunkZ; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
