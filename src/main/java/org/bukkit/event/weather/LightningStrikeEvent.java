package org.bukkit.event.weather;

import org.bukkit.World;

public class LightningStrikeEvent extends WeatherEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public LightningStrikeEvent(World world) { super(world); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}