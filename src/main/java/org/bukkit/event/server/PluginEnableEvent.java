package org.bukkit.event.server;

import org.bukkit.plugin.Plugin;

public class PluginEnableEvent extends ServerEvent {
    public PluginEnableEvent(Plugin plugin) {}
    public Plugin getPlugin() { return null; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}