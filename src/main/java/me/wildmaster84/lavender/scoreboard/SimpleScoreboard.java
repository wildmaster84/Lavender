package me.wildmaster84.lavender.scoreboard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.*;

public class SimpleScoreboard implements Scoreboard {

    private final Map<String, SimpleObjective> objectives = new LinkedHashMap<>();
    private final Map<DisplaySlot, SimpleObjective> slotObjectives = new HashMap<>();
    private final Map<String, SimpleTeam> teams = new LinkedHashMap<>();
    private Sidebar sidebar;
    private final Set<Player> viewers = new LinkedHashSet<>();

    @Override
    public Objective registerObjective(String name, Criteria criteria) {
        if (objectives.containsKey(name)) {
            throw new IllegalArgumentException("Objective already exists: " + name);
        }
        SimpleObjective obj = new SimpleObjective(name, this, criteria);
        objectives.put(name, obj);
        return obj;
    }

    @Override
    public Objective registerNewObjective(String name, String criteria) {
        return registerObjective(name, new SimpleCriteria(criteria, false, RenderType.INTEGER));
    }

    @Override
    public Objective getObjective(String name) {
        return objectives.get(name);
    }

    @Override
    public Objective getObjective(DisplaySlot slot) {
        return slotObjectives.get(slot);
    }

    @Override
    public Set<Objective> getObjectives() {
        return new LinkedHashSet<>(objectives.values());
    }

    @Override
    public Set<Objective> getObjectivesByCriteria(Criteria criteria) {
        Set<Objective> result = new LinkedHashSet<>();
        for (SimpleObjective obj : objectives.values()) {
            if (obj.getCriteria().equals(criteria.getName())) result.add(obj);
        }
        return result;
    }

    @Override
    public Set<String> getObjectiveNames() {
        return new LinkedHashSet<>(objectives.keySet());
    }

    @Override
    public void unregisterObjective(Objective objective) {
        if (objective instanceof SimpleObjective simpleObj) {
            objectives.remove(simpleObj.getName());
            DisplaySlot slot = simpleObj.getDisplaySlot();
            if (slot != null) slotObjectives.remove(slot);
            if (slot == DisplaySlot.SIDEBAR && sidebar != null) {
                for (Player viewer : viewers) {
                    sidebar.removeViewer(viewer);
                }
                sidebar = null;
            }
        }
    }

    @Override
    public Team registerTeam(String name) {
        if (teams.containsKey(name)) {
            throw new IllegalArgumentException("Team already exists: " + name);
        }
        SimpleTeam team = new SimpleTeam(name, this);
        teams.put(name, team);
        return team;
    }

    @Override
    public Team registerNewTeam(String name) {
        return registerTeam(name);
    }

    @Override
    public Team getTeam(String name) {
        return teams.get(name);
    }

    @Override
    public Set<Team> getTeams() {
        return new LinkedHashSet<>(teams.values());
    }

    @Override
    public Team getEntryTeam(String entry) {
        for (SimpleTeam team : teams.values()) {
            if (team.hasEntry(entry)) return team;
        }
        return null;
    }

    @Override
    public Team getPlayerTeam(OfflinePlayer player) {
        return getEntryTeam(player.getName());
    }

    @Override
    public void unregisterTeam(Team team) {
        if (team instanceof SimpleTeam simpleTeam) {
            teams.remove(simpleTeam.getName());
        }
    }

    @Override
    public void resetScores(String entry) {
        for (SimpleObjective obj : objectives.values()) {
            obj.resetEntryScore(entry);
        }
        if (sidebar != null) {
            sidebar.removeLine(entry);
        }
    }

    @Override
    public void resetScores(OfflinePlayer player) {
        if (player.getName() != null) resetScores(player.getName());
    }

    @Override
    public Set<Score> getScores(OfflinePlayer player) {
        return player.getName() != null ? getScores(player.getName()) : Collections.emptySet();
    }

    @Override
    public Set<Score> getScores(String entry) {
        Set<Score> result = new LinkedHashSet<>();
        for (SimpleObjective obj : objectives.values()) {
            Score score = obj.getEntryScore(entry);
            if (score != null) result.add(score);
        }
        return result;
    }

    @Override
    public void clearSlot(DisplaySlot slot) {
        SimpleObjective obj = slotObjectives.remove(slot);
        if (obj != null) {
            obj.displaySlot = null;
        }
        if (slot == DisplaySlot.SIDEBAR && sidebar != null) {
            for (Player viewer : viewers) {
                sidebar.removeViewer(viewer);
            }
            sidebar = null;
        }
    }

    @Override
    public Set<String> getEntries() {
        Set<String> entries = new LinkedHashSet<>();
        for (SimpleObjective obj : objectives.values()) {
            entries.addAll(obj.entryScores.keySet());
        }
        return entries;
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        return Collections.emptySet();
    }

    public boolean addViewer(Player player) {
        boolean added = viewers.add(player);
        if (added && sidebar != null) {
            sidebar.addViewer(player);
        }
        return added;
    }

    public boolean removeViewer(Player player) {
        boolean removed = viewers.remove(player);
        if (removed && sidebar != null) {
            sidebar.removeViewer(player);
        }
        return removed;
    }

    void onDisplaySlotSet(SimpleObjective objective, DisplaySlot slot) {
        if (slot == DisplaySlot.SIDEBAR) {
            if (sidebar == null) {
                sidebar = new Sidebar(Component.text(objective.getDisplayName()));
            }
            for (Player viewer : viewers) {
                sidebar.addViewer(viewer);
            }
        }
    }

    void onDisplayNameChanged(SimpleObjective objective, String displayName) {
        if (objective.getDisplaySlot() == DisplaySlot.SIDEBAR && sidebar != null) {
            sidebar.setTitle(displayName);
        }
    }

    void onScoreSet(String entry, int score) {
        if (sidebar != null) {
            if (sidebar.getLine(entry) != null) {
                sidebar.updateLineScore(entry, score);
            } else {
                try {
                    Component content = buildLineContent(entry);
                    sidebar.createLine(new Sidebar.ScoreboardLine(entry, content, score));
                } catch (Exception e) {
                    // Line might already exist or max lines reached
                }
            }
        }
    }

    void onScoreReset(String entry) {
        if (sidebar != null) {
            sidebar.removeLine(entry);
        }
    }

    void onTeamUpdated(String entry) {
        if (sidebar != null && sidebar.getLine(entry) != null) {
            Component content = buildLineContent(entry);
            sidebar.updateLineContent(entry, content);
        }
    }

    private Component buildLineContent(String entry) {
        SimpleTeam team = (SimpleTeam) getEntryTeam(entry);
        if (team == null) {
            return Component.text(entry);
        }
        String prefix = team.getPrefix();
        String suffix = team.getSuffix();
        String full = prefix + entry + suffix;
        return LegacyComponentSerializer.legacyAmpersand().deserialize(full);
    }

    public static class SimpleObjective implements Objective {
        private final String name;
        private final SimpleScoreboard scoreboard;
        private final Criteria criteria;
        private String displayName;
        private DisplaySlot displaySlot;
        private RenderType renderType = RenderType.INTEGER;
        private final Map<String, Integer> entryScores = new LinkedHashMap<>();

        SimpleObjective(String name, SimpleScoreboard scoreboard, Criteria criteria) {
            this.name = name;
            this.scoreboard = scoreboard;
            this.criteria = criteria;
            this.displayName = name;
        }

        @Override public String getName() { return name; }
        @Override public String getDisplayName() { return displayName; }
        @Override public void setDisplayName(String displayName) {
            this.displayName = displayName;
            scoreboard.onDisplayNameChanged(this, displayName);
        }
        @Override public String getCriteria() { return criteria.getName(); }
        @Override public Criteria getTrackedCriteria() { return criteria; }
        @Override public boolean isModifiable() { return !criteria.isReadOnly(); }
        @Override public DisplaySlot getDisplaySlot() { return displaySlot; }
        @Override public void setDisplaySlot(DisplaySlot slot) {
            if (displaySlot != null) scoreboard.slotObjectives.remove(displaySlot);
            this.displaySlot = slot;
            if (slot != null) {
                scoreboard.slotObjectives.put(slot, this);
                scoreboard.onDisplaySlotSet(this, slot);
            }
        }
        @Override public RenderType getRenderType() { return renderType; }
        @Override public void setRenderType(RenderType renderType) { this.renderType = renderType; }

        @Override
        public Score getScore(OfflinePlayer player) {
            return getScore(player.getName() != null ? player.getName() : player.getUniqueId().toString());
        }

        @Override
        public Score getScore(String entry) {
            int currentScore = entryScores.getOrDefault(entry, 0);
            return new SimpleScore(entry, this, scoreboard, currentScore);
        }

        @Override
        public void unregister() {
            scoreboard.unregisterObjective(this);
        }

        @Override
        public Scoreboard getScoreboard() { return scoreboard; }

        void setEntryScore(String entry, int score) {
            entryScores.put(entry, score);
            scoreboard.onScoreSet(entry, score);
        }

        int getEntryScoreValue(String entry) {
            return entryScores.getOrDefault(entry, 0);
        }

        Score getEntryScore(String entry) {
            if (!entryScores.containsKey(entry)) return null;
            return new SimpleScore(entry, this, scoreboard, entryScores.get(entry));
        }

        void resetEntryScore(String entry) {
            if (entryScores.remove(entry) != null) {
                scoreboard.onScoreReset(entry);
            }
        }
    }

    public static class SimpleScore implements Score {
        private final String entry;
        private final SimpleObjective objective;
        private final SimpleScoreboard scoreboard;
        private int score;

        SimpleScore(String entry, SimpleObjective objective, SimpleScoreboard scoreboard, int initialScore) {
            this.entry = entry;
            this.objective = objective;
            this.scoreboard = scoreboard;
            this.score = initialScore;
        }

        @Override public OfflinePlayer getPlayer() { return null; }
        @Override public String getEntry() { return entry; }
        @Override public int getScore() { return score; }
        @Override public void setScore(int score) {
            this.score = score;
            objective.setEntryScore(entry, score);
        }
        @Override public boolean isScoreSet() { return objective.entryScores.containsKey(entry); }
        @Override public void resetScore() { objective.resetEntryScore(entry); }
        @Override public Objective getObject() { return objective; }
        @Override public Scoreboard getScoreboard() { return scoreboard; }
    }

    public static class SimpleTeam implements Team {
        private final String name;
        private final SimpleScoreboard scoreboard;
        private String displayName;
        private String prefix = "";
        private String suffix = "";
        private boolean allowFriendlyFire = false;
        private boolean canSeeFriendlyInvisibles = false;
        private org.bukkit.ChatColor color = org.bukkit.ChatColor.RESET;
        private final Set<String> entries = new LinkedHashSet<>();
        private final Set<OfflinePlayer> players = new LinkedHashSet<>();
        private final Map<Option, OptionStatus> options = new HashMap<>();

        SimpleTeam(String name, SimpleScoreboard scoreboard) {
            this.name = name;
            this.scoreboard = scoreboard;
            this.displayName = name;
        }

        @Override public String getName() { return name; }
        @Override public String getDisplayName() { return displayName; }
        @Override public void setDisplayName(String displayName) { this.displayName = displayName; }
        @Override public String getPrefix() { return prefix; }
        @Override public void setPrefix(String prefix) {
            this.prefix = prefix;
            for (String entry : entries) {
                scoreboard.onTeamUpdated(entry);
            }
        }
        @Override public String getSuffix() { return suffix; }
        @Override public void setSuffix(String suffix) {
            this.suffix = suffix;
            for (String entry : entries) {
                scoreboard.onTeamUpdated(entry);
            }
        }
        @Override public boolean allowFriendlyFire() { return allowFriendlyFire; }
        @Override public void setAllowFriendlyFire(boolean enabled) { this.allowFriendlyFire = enabled; }
        @Override public boolean canSeeFriendlyInvisibles() { return canSeeFriendlyInvisibles; }
        @Override public void setCanSeeFriendlyInvisibles(boolean enabled) { this.canSeeFriendlyInvisibles = enabled; }
        @Override public org.bukkit.ChatColor getColor() { return color; }
        @Override public void setColor(org.bukkit.ChatColor color) { this.color = color; }
        @Override public Set<String> getEntries() { return new LinkedHashSet<>(entries); }
        @Override public Set<OfflinePlayer> getPlayers() { return new LinkedHashSet<>(players); }
        @Override public int getSize() { return entries.size(); }
        @Override public Scoreboard getScoreboard() { return scoreboard; }
        @Override public void unregister() { scoreboard.unregisterTeam(this); }
        @Override public void addEntry(String entry) { entries.add(entry); }
        @Override public void removeEntry(String entry) { entries.remove(entry); }
        @Override public boolean hasEntry(String entry) { return entries.contains(entry); }
        @Override public void addPlayer(OfflinePlayer player) { players.add(player); if (player.getName() != null) entries.add(player.getName()); }
        @Override public void removePlayer(OfflinePlayer player) { players.remove(player); if (player.getName() != null) entries.remove(player.getName()); }
        @Override public boolean hasPlayer(OfflinePlayer player) { return players.contains(player); }
        @Override public OptionStatus getOption(Option option) { return options.getOrDefault(option, OptionStatus.ALWAYS); }
        @Override public void setOption(Option option, OptionStatus status) { options.put(option, status); }
    }

    public static class SimpleCriteria implements Criteria {
        private final String name;
        private final boolean readOnly;
        private final RenderType defaultRenderType;

        public SimpleCriteria() {
            this(DUMMY, false, RenderType.INTEGER);
        }

        public SimpleCriteria(String name, boolean readOnly, RenderType defaultRenderType) {
            this.name = name;
            this.readOnly = readOnly;
            this.defaultRenderType = defaultRenderType;
        }

        @Override public String getName() { return name; }
        @Override public boolean isReadOnly() { return readOnly; }
        @Override public RenderType getDefaultRenderType() { return defaultRenderType; }
    }
}
