package org.bukkit.event.server;

import org.bukkit.event.Event;

public abstract class ServerEvent extends Event {
    @Override public abstract org.bukkit.event.HandlerList getHandlers();
}