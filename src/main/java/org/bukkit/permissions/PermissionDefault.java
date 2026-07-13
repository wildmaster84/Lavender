package org.bukkit.permissions;

public enum PermissionDefault {
    TRUE, FALSE, OP, NOT_OP;
    public boolean getValue(boolean op) { return this == TRUE || (this == OP && op) || (this == NOT_OP && !op); }
}
