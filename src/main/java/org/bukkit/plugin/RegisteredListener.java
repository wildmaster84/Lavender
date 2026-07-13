package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class RegisteredListener {
    private final Listener listener;
    private final EventPriority priority;
    private final Plugin plugin;

    public RegisteredListener(Listener listener, EventPriority priority, Plugin plugin) {
        this.listener = listener;
        this.priority = priority;
        this.plugin = plugin;
    }

    public Listener getListener() { return listener; }
    public EventPriority getPriority() { return priority; }
    public Plugin getPlugin() { return plugin; }

    public void callEvent(Event event) {}
}
