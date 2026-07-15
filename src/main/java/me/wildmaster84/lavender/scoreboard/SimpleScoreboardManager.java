package me.wildmaster84.lavender.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SimpleScoreboardManager implements ScoreboardManager {
    @Override
    public Scoreboard getMainScoreboard() {
        return new SimpleScoreboard();
    }

    @Override
    public Scoreboard getNewScoreboard() {
        return new SimpleScoreboard();
    }
}
