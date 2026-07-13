package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;
import java.util.Set;

public interface PluginManager {
    Plugin getPlugin(String name);
    Plugin[] getPlugins();
    boolean isPluginEnabled(String name);
    boolean isPluginEnabled(Plugin plugin);
    void loadPlugins();
    void enablePlugin(Plugin plugin);
    void disablePlugin(Plugin plugin);
    void registerEvents(Listener listener, Plugin plugin);
    void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin);
    void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled);
    void callEvent(Event event);
    List<Listener> getRegisteredListeners();
    void addPermission(Permission perm);
    void removePermission(Permission perm);
    Permission getPermission(String name);
    Set<Permission> getPermissions();
    boolean addPermission(Permission perm, PermissionDefault defaultValue);
    void recalculatePermissionDefaults(Permission perm);
    void subscribeToPermission(String permission, Permissible permissible);
    void unsubscribeFromPermission(String permission, Permissible permissible);
    Set<Permissible> getPermissionSubscriptions(String permission, Plugin plugin);
    Set<Permissible> getDefaultPermSubscriptions(PermissionDefault defaultValue);
    void recalculatePermissionDefaults();
}
