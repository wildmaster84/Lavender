package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import java.util.Set;

public interface Scoreboard {
    Objective registerObjective(String name, Criteria criteria);
    Objective getObjective(String name);
    Objective getObjective(DisplaySlot slot);
    Set<Objective> getObjectives();
    Set<Objective> getObjectivesByCriteria(Criteria criteria);
    Set<String> getObjectiveNames();
    void unregisterObjective(Objective objective);
    Team registerTeam(String name);
    Team getTeam(String name);
    Set<Team> getTeams();
    Team getEntryTeam(String entry);
    Team getPlayerTeam(OfflinePlayer player);
    void unregisterTeam(Team team);
    void resetScores(String entry);
    void resetScores(OfflinePlayer player);
    Set<Score> getScores(OfflinePlayer player);
    Set<Score> getScores(String entry);
    void clearSlot(DisplaySlot slot);
    Set<String> getEntries();
    Set<OfflinePlayer> getPlayers();
}
