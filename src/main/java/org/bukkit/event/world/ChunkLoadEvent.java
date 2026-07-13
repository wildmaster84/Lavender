package org.bukkit.event.world;

import org.bukkit.Chunk;

public class ChunkLoadEvent extends ChunkEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public ChunkLoadEvent(Chunk chunk) { super(chunk); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}