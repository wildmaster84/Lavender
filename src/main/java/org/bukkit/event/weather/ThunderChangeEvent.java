package org.bukkit.event.weather;

import org.bukkit.World;

public class ThunderChangeEvent extends WeatherEvent {
    public ThunderChangeEvent(World world, boolean to) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}