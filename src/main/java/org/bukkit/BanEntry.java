package org.bukkit;

public interface BanEntry {
    String getBanTarget();
    String getReason();
    String getSource();
    long getCreated();
    long getExpiration();
}
