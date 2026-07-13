package me.wildmaster84.lavender.event;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.GlobalEventHandler;

public class EventManager {
    private final GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();

    public EventManager getEventManager() {
        return this;
    }

    public void addListener(EventListener<? extends Event> listener) {
        handler.addListener(listener);
    }

    public void removeListener(EventListener<? extends Event> listener) {
        handler.removeListener(listener);
    }
}
