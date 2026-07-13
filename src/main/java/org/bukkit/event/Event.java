package org.bukkit.event;

public abstract class Event {
    private String name;
    private final boolean async;

    public Event() {
        this(false);
    }

    public Event(boolean isAsync) {
        this.async = isAsync;
    }

    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public boolean isAsynchronous() {
        return async;
    }

    public boolean callEvent() {
        org.bukkit.Bukkit.getPluginManager().callEvent(this);
        return true;
    }

    public abstract HandlerList getHandlers();
}
