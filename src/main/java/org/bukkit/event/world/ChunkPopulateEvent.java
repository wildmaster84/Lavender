package org.bukkit.event.world;

import org.bukkit.Chunk;

public class ChunkPopulateEvent extends ChunkEvent {
    public ChunkPopulateEvent(Chunk chunk) { super(chunk); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}