package io.papermc.paper.event.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChunkUnloadEvent extends Event {
    private final Player player;
    private final int chunkX;
    private final int chunkZ;
    public PlayerChunkUnloadEvent(Player player, int chunkX, int chunkZ) {
        this.player = player;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }
    public Player getPlayer() { return player; }
    public int getChunkX() { return chunkX; }
    public int getChunkZ() { return chunkZ; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
