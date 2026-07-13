package org.bukkit.permissions;

import java.util.Map;

public class Permission {
    private final String name;
    private String description;
    private PermissionDefault defaultValue;

    public Permission(String name) {
        this.name = name;
        this.description = "";
        this.defaultValue = PermissionDefault.OP;
    }

    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
        this.defaultValue = PermissionDefault.OP;
    }

    public Permission(String name, PermissionDefault defaultValue) {
        this.name = name;
        this.description = "";
        this.defaultValue = defaultValue;
    }

    public Permission(String name, String description, PermissionDefault defaultValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public Permission(String name, Map<String, Object> data) {
        this.name = name;
        this.description = data != null && data.containsKey("description") ? String.valueOf(data.get("description")) : "";
        this.defaultValue = data != null && data.containsKey("default") ? PermissionDefault.valueOf(String.valueOf(data.get("default"))) : PermissionDefault.OP;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public PermissionDefault getDefault() { return defaultValue; }
    public void setDefault(PermissionDefault value) { this.defaultValue = value; }

    public static Permission loadPermission(String name, Map<String, Object> data) {
        return new Permission(name);
    }
}
