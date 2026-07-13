package org.bukkit.command;

import java.util.List;

public class Command {
    private final String name;
    private String label;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;
    private String permissionMessage;

    public Command(String name) { this.name = name; this.label = name; }
    public Command(String name, String description, String usage, List<String> aliases) {
        this.name = name;
        this.label = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Command description(String description) { this.description = description; return this; }
    public String getUsage() { return usage; }
    public void setUsage(String usage) { this.usage = usage; }
    public Command usage(String usage) { this.usage = usage; return this; }
    public List<String> getAliases() { return aliases; }
    public Command setAliases(List<String> aliases) { this.aliases = aliases; return this; }
    public String getPermission() { return permission; }
    public void setPermission(String permission) { this.permission = permission; }
    public Command permission(String permission) { this.permission = permission; return this; }
    public String getPermissionMessage() { return permissionMessage; }
    public void setPermissionMessage(String permissionMessage) { this.permissionMessage = permissionMessage; }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) { return false; }
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) { return java.util.Collections.emptyList(); }
    public boolean testPermission(CommandSender target) { return true; }
    public boolean testPermissionSilent(CommandSender target) { return true; }
    public String getLabel() { return label; }
    public boolean setLabel(String label) { this.label = label; return true; }
    public boolean setName(String name) { return true; }
    public boolean isRegistered() { return true; }
    public boolean register(CommandMap commandMap) { return true; }
    public boolean unregister(CommandMap commandMap) { return true; }
}
