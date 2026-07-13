package me.wildmaster84.adapter.server;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Score;
import org.bukkit.OfflinePlayer;
import java.util.Set;

public class SimpleScoreboard implements Scoreboard {
    @Override public Objective registerObjective(String name, Criteria criteria) { return null; }
    @Override public Objective getObjective(String name) { return null; }
    @Override public Objective getObjective(DisplaySlot slot) { return null; }
    @Override public Set<Objective> getObjectives() { return Set.of(); }
    @Override public Set<Objective> getObjectivesByCriteria(Criteria criteria) { return Set.of(); }
    @Override public Set<String> getObjectiveNames() { return Set.of(); }
    @Override public void unregisterObjective(Objective objective) {}
    @Override public Team registerTeam(String name) { return null; }
    @Override public Team getTeam(String name) { return null; }
    @Override public Set<Team> getTeams() { return Set.of(); }
    @Override public Team getEntryTeam(String entry) { return null; }
    @Override public Team getPlayerTeam(OfflinePlayer player) { return null; }
    @Override public void unregisterTeam(Team team) {}
    @Override public void resetScores(String entry) {}
    @Override public void resetScores(OfflinePlayer player) {}
    @Override public Set<Score> getScores(OfflinePlayer player) { return Set.of(); }
    @Override public Set<Score> getScores(String entry) { return Set.of(); }
    @Override public void clearSlot(DisplaySlot slot) {}
    @Override public Set<String> getEntries() { return Set.of(); }
    @Override public Set<OfflinePlayer> getPlayers() { return Set.of(); }
}
