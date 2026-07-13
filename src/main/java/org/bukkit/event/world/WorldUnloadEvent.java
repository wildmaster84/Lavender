package org.bukkit.event.world;

import org.bukkit.World;

public class WorldUnloadEvent extends WorldEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public WorldUnloadEvent(World world) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}