package org.bukkit.event.server;

import org.bukkit.plugin.Plugin;

public class PluginDisableEvent extends ServerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public PluginDisableEvent(Plugin plugin) {}
    public Plugin getPlugin() { return null; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}