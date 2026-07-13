package org.bukkit.permissions;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;

public class PermissionAttachment {
    private final Plugin plugin;
    private final Permissible permissible;
    private final Map<String, Boolean> permissions = new HashMap<>();

    public PermissionAttachment(Plugin plugin) {
        this(plugin, null);
    }

    public PermissionAttachment(Plugin plugin, Permissible permissible) {
        this.plugin = plugin;
        this.permissible = permissible;
    }

    public void setPermission(String name, boolean value) {
        permissions.put(name.toLowerCase(java.util.Locale.ROOT), value);
        if (permissible != null) permissible.recalculatePermissions();
    }

    public void setPermission(Permission permission, boolean value) {
        setPermission(permission.getName(), value);
    }

    public void unsetPermission(String name) {
        permissions.remove(name.toLowerCase(java.util.Locale.ROOT));
        if (permissible != null) permissible.recalculatePermissions();
    }

    public void unsetPermission(Permission permission) {
        unsetPermission(permission.getName());
    }

    public void remove() {
        if (permissible instanceof PermissibleBase) {
            ((PermissibleBase) permissible).removeAttachment(this);
        }
    }

    public Plugin getPlugin() { return plugin; }
    public Permissible getPermissible() { return permissible; }
    public Map<String, Boolean> getPermissions() { return permissions; }
}
