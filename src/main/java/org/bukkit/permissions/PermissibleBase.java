package org.bukkit.permissions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.plugin.Plugin;

public class PermissibleBase implements Permissible {
    private final ServerOperator opable;
    private final List<PermissionAttachment> attachments = new ArrayList<>();
    private final Map<String, Boolean> permissions = new HashMap<>();

    public PermissibleBase(ServerOperator opable) {
        this.opable = opable;
    }

    @Override
    public boolean isPermissionSet(String name) {
        if (name == null) return false;
        return permissions.containsKey(name.toLowerCase(Locale.ROOT));
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        if (perm == null) return false;
        return isPermissionSet(perm.getName());
    }

    @Override
    public boolean hasPermission(String name) {
        if (name == null) return false;
        String key = name.toLowerCase(Locale.ROOT);
        if (permissions.containsKey(key)) return permissions.get(key);
        return false;
    }

    @Override
    public boolean hasPermission(Permission perm) {
        if (perm == null) return false;
        return hasPermission(perm.getName());
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        PermissionAttachment att = new PermissionAttachment(plugin, this);
        att.setPermission(name, value);
        attachments.add(att);
        recalculatePermissions();
        return att;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        PermissionAttachment att = new PermissionAttachment(plugin, this);
        attachments.add(att);
        return att;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        PermissionAttachment att = addAttachment(plugin, name, value);
        org.bukkit.Bukkit.getScheduler().runTaskLater(plugin, () -> removeAttachment(att), ticks);
        return att;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        PermissionAttachment att = addAttachment(plugin);
        org.bukkit.Bukkit.getScheduler().runTaskLater(plugin, () -> removeAttachment(att), ticks);
        return att;
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        if (attachment == null) return;
        attachments.removeIf(a -> a == attachment);
        recalculatePermissions();
    }

    @Override
    public void recalculatePermissions() {
        permissions.clear();
        for (PermissionAttachment att : attachments) {
            for (Map.Entry<String, Boolean> entry : att.getPermissions().entrySet()) {
                permissions.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
            }
        }
    }

    @Override
    public Set<PermissionAttachment> getEffectivePermissions() {
        return new HashSet<>(attachments);
    }

    @Override
    public boolean isOp() {
        return opable != null && opable.isOp();
    }

    @Override
    public void setOp(boolean value) {
        if (opable != null) opable.setOp(value);
    }
}
