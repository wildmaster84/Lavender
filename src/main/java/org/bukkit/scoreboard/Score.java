package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;

public interface Score {
    OfflinePlayer getPlayer();
    String getEntry();
    int getScore();
    void setScore(int score);
    boolean isScoreSet();
    void resetScore();
    Objective getObject();
    Scoreboard getScoreboard();
}
