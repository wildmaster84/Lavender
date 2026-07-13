package org.bukkit.event.world;

import org.bukkit.World;

public class WorldUnloadEvent extends WorldEvent {
    public WorldUnloadEvent(World world) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}