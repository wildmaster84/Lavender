package org.bukkit.event.weather;

import org.bukkit.World;

public class ThunderChangeEvent extends WeatherEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public ThunderChangeEvent(World world, boolean to) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}