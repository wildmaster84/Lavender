package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import java.util.Set;

public interface Team {
    String getName();
    String getDisplayName();
    void setDisplayName(String displayName);
    String getPrefix();
    void setPrefix(String prefix);
    String getSuffix();
    void setSuffix(String suffix);
    boolean allowFriendlyFire();
    void setAllowFriendlyFire(boolean enabled);
    boolean canSeeFriendlyInvisibles();
    void setCanSeeFriendlyInvisibles(boolean enabled);
    org.bukkit.ChatColor getColor();
    void setColor(org.bukkit.ChatColor color);
    Set<String> getEntries();
    Set<OfflinePlayer> getPlayers();
    int getSize();
    Scoreboard getScoreboard();
    void unregister();
    void addEntry(String entry);
    void removeEntry(String entry);
    boolean hasEntry(String entry);
    void addPlayer(OfflinePlayer player);
    void removePlayer(OfflinePlayer player);
    boolean hasPlayer(OfflinePlayer player);
    org.bukkit.scoreboard.Team.OptionStatus getOption(org.bukkit.scoreboard.Team.Option option);
    void setOption(org.bukkit.scoreboard.Team.Option option, org.bukkit.scoreboard.Team.OptionStatus status);

    enum Option { NAME_TAG_VISIBILITY, DEATH_MESSAGE_VISIBILITY, COLLISION_RULE }
    enum OptionStatus { ALWAYS, NEVER, FOR_OTHER_TEAMS, FOR_OWN_TEAM }
}
