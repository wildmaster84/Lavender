package org.bukkit.command;

public interface CommandSender extends net.kyori.adventure.audience.Audience {
    void sendMessage(String message);
    void sendMessage(String[] messages);
    void sendMessage(java.util.UUID sender, String message);
    void sendMessage(java.util.UUID sender, String[] messages);
    String getName();
    boolean isOp();
    void setOp(boolean value);
    java.util.Set<org.bukkit.permissions.PermissionAttachment> getEffectivePermissions();
    org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value);
    org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin);
    void recalculatePermissions();
    boolean hasPermission(String name);
    boolean hasPermission(org.bukkit.permissions.Permission perm);
    org.bukkit.Server getServer();
    org.bukkit.command.ConsoleCommandSender getConsoleSender();
}
