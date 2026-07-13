package org.bukkit.event.world;

import org.bukkit.World;

public class WorldInitEvent extends WorldEvent {
    public WorldInitEvent(World world) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}