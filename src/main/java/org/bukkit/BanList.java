package org.bukkit;

public interface BanList {

    enum Type {
        NAME,
        IP
    }

    BanEntry getBanEntry(String target);
    void addBan(String target, String reason, java.util.Date expires, String source);
    boolean isBanned(String target);
    void pardon(String target);
}
