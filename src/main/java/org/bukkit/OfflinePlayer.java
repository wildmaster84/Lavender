package org.bukkit;

import java.util.UUID;

public interface OfflinePlayer {
    boolean isOnline();
    String getName();
    UUID getUniqueId();
    boolean isOp();
    void setOp(boolean op);
    long getFirstPlayed();
    long getLastPlayed();
    boolean hasPlayedBefore();
    long getLastSeen();
    boolean isBanned();
    boolean isWhitelisted();
    void setWhitelisted(boolean value);
    org.bukkit.entity.Player getPlayer();
}
