package me.wildmaster84.adapter.server;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SimpleScoreboardManager implements ScoreboardManager {
    private final Scoreboard main = new SimpleScoreboard();

    @Override public Scoreboard getMainScoreboard() { return main; }
    @Override public Scoreboard getNewScoreboard() { return new SimpleScoreboard(); }
}
