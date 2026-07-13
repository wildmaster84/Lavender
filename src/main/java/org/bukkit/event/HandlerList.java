package org.bukkit.event;

import org.bukkit.plugin.RegisteredListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HandlerList {
    private static final List<HandlerList> allLists = new ArrayList<>();
    private final List<RegisteredListener> handlers = new ArrayList<>();

    static Consumer<org.bukkit.event.Listener> unregisterCallback;

    public static void setUnregisterCallback(Consumer<org.bukkit.event.Listener> callback) {
        unregisterCallback = callback;
    }

    public HandlerList() {
        allLists.add(this);
    }

    public void register(RegisteredListener listener) {
        handlers.add(listener);
    }

    public void unregister(RegisteredListener listener) {
        handlers.remove(listener);
    }

    public RegisteredListener[] getRegisteredListeners() {
        return handlers.toArray(new RegisteredListener[0]);
    }

    public static List<HandlerList> getHandlerLists() {
        return new ArrayList<>(allLists);
    }

    public void bake() {}

    public static void unregisterAll(org.bukkit.event.Listener listener) {
        for (HandlerList list : allLists) {
            list.handlers.removeIf(h -> h.getListener() == listener);
        }
        if (unregisterCallback != null) {
            unregisterCallback.accept(listener);
        }
    }

    public static void unregisterAll(org.bukkit.plugin.Plugin plugin) {
        for (HandlerList list : allLists) {
            list.handlers.removeIf(h -> h.getPlugin() == plugin);
        }
    }
}
