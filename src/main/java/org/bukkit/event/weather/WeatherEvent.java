package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.event.Event;

public abstract class WeatherEvent extends Event {
    protected World world;
    public WeatherEvent(World world) { this.world = world; }
    public World getWorld() { return world; }
}