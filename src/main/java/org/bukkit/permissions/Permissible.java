package org.bukkit.permissions;

public interface Permissible extends ServerOperator {
    boolean isPermissionSet(String name);
    boolean isPermissionSet(Permission perm);
    boolean hasPermission(String name);
    boolean hasPermission(Permission perm);
    PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value);
    PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin);
    PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value, int ticks);
    PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, int ticks);
    void removeAttachment(PermissionAttachment attachment);
    void recalculatePermissions();
    java.util.Set<PermissionAttachment> getEffectivePermissions();
    boolean isOp();
    void setOp(boolean value);
}
