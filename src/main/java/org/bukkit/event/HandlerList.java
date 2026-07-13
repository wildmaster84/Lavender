package org.bukkit.event;

import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HandlerList {
    private final List<RegisteredListener> handlers = new ArrayList<>();

    static Consumer<org.bukkit.event.Listener> unregisterCallback;
    static Consumer<Plugin> unregisterPluginCallback;

    public static void setUnregisterCallback(Consumer<org.bukkit.event.Listener> callback) {
        unregisterCallback = callback;
    }

    public static void setUnregisterPluginCallback(Consumer<Plugin> callback) {
        unregisterPluginCallback = callback;
    }

    public HandlerList() {}

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
        return new ArrayList<>();
    }

    public void bake() {}

    public static void unregisterAll(org.bukkit.event.Listener listener) {
        if (unregisterCallback != null) {
            unregisterCallback.accept(listener);
        }
    }

    public static void unregisterAll(org.bukkit.plugin.Plugin plugin) {
        if (unregisterPluginCallback != null) {
            unregisterPluginCallback.accept(plugin);
        }
    }
}
