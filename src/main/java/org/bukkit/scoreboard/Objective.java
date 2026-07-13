package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;

public interface Objective {
    String getName();
    String getDisplayName();
    void setDisplayName(String displayName);
    String getCriteria();
    Criteria getTrackedCriteria();
    boolean isModifiable();
    DisplaySlot getDisplaySlot();
    void setDisplaySlot(DisplaySlot slot);
    org.bukkit.scoreboard.RenderType getRenderType();
    void setRenderType(org.bukkit.scoreboard.RenderType renderType);
    Score getScore(OfflinePlayer player);
    Score getScore(String entry);
    void unregister();
    Scoreboard getScoreboard();
}
