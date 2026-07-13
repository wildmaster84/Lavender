package org.bukkit.plugin;

import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.*;

public class SimplePluginManager implements PluginManager {
    public final CommandMap commandMap = new SimpleCommandMap();
    protected final Map<String, Permission> permissions = new HashMap<>();

    @Override
    public void registerEvents(Listener listener, Plugin plugin) {}

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin) {}

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {}

    @Override
    public void callEvent(Event event) {}

    @Override
    public void enablePlugin(Plugin plugin) {}

    @Override
    public void disablePlugin(Plugin plugin) {}

    @Override
    public void loadPlugins() {}

    @Override
    public Plugin[] getPlugins() { return new Plugin[0]; }

    @Override
    public boolean isPluginEnabled(String name) { return false; }

    @Override
    public boolean isPluginEnabled(Plugin plugin) { return false; }

    @Override
    public Plugin getPlugin(String name) { return null; }

    @Override
    public List<Listener> getRegisteredListeners() { return Collections.emptyList(); }

    @Override
    public void addPermission(Permission perm) { permissions.put(perm.getName().toLowerCase(), perm); }

    @Override
    public void removePermission(Permission perm) { permissions.remove(perm.getName().toLowerCase()); }

    @Override
    public Permission getPermission(String name) { return permissions.get(name.toLowerCase()); }

    @Override
    public Set<Permission> getPermissions() { return new HashSet<>(permissions.values()); }

    @Override
    public boolean addPermission(Permission perm, PermissionDefault defaultValue) { addPermission(perm); return true; }

    @Override
    public void recalculatePermissionDefaults(Permission perm) {}

    @Override
    public void subscribeToPermission(String permission, Permissible permissible) {}

    @Override
    public void unsubscribeFromPermission(String permission, Permissible permissible) {}

    @Override
    public Set<Permissible> getPermissionSubscriptions(String permission, Plugin plugin) { return Collections.emptySet(); }

    @Override
    public Set<Permissible> getDefaultPermSubscriptions(PermissionDefault defaultValue) { return Collections.emptySet(); }

    @Override
    public void recalculatePermissionDefaults() {}
}
