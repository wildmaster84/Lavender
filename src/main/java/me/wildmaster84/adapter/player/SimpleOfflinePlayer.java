package me.wildmaster84.adapter.player;

import org.bukkit.OfflinePlayer;
import java.util.UUID;

public class SimpleOfflinePlayer implements OfflinePlayer {
    private final String name;
    private final UUID uuid;

    public SimpleOfflinePlayer(String name) {
        this.name = name;
        this.uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes());
    }

    @Override public boolean isOnline() { return false; }
    @Override public String getName() { return name; }
    @Override public UUID getUniqueId() { return uuid; }
    @Override public boolean isOp() { return false; }
    @Override public void setOp(boolean op) {}
    @Override public long getFirstPlayed() { return 0; }
    @Override public long getLastPlayed() { return 0; }
    @Override public boolean hasPlayedBefore() { return false; }
    @Override public long getLastSeen() { return 0; }
    @Override public boolean isBanned() { return false; }
    @Override public boolean isWhitelisted() { return false; }
    @Override public void setWhitelisted(boolean value) {}
    @Override public org.bukkit.entity.Player getPlayer() { return null; }
}
