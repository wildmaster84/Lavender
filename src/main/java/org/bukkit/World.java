package org.bukkit;

import org.bukkit.entity.Player;

public interface World extends org.bukkit.generator.WorldInfo {
    String getName();
    java.util.UUID getUID();
    Location getSpawnLocation();
    java.util.List<Player> getPlayers();
    long getTime();
    void setTime(long time);
    long getFullTime();
    void setFullTime(long time);
    boolean hasStorm();
    void setStorm(boolean hasStorm);
    boolean isDayTime();
    int getMaxHeight();
    int getSeaLevel();
    long getSeed();
    boolean getPVP();
    void setPVP(boolean pvp);
    org.bukkit.Difficulty getDifficulty();
    void setDifficulty(org.bukkit.Difficulty difficulty);
    org.bukkit.World.Environment getEnvironment();
    org.bukkit.WorldBorder getWorldBorder();
    void save();
    boolean isAutoSave();
    void setAutoSave(boolean autoSave);
    <T extends org.bukkit.entity.Entity> T spawn(Location location, Class<T> clazz);
    org.bukkit.entity.Item dropItem(Location location, org.bukkit.inventory.ItemStack item);
    void playSound(Location location, org.bukkit.Sound sound, float volume, float pitch);
    void playSound(Location location, String sound, float volume, float pitch);
    java.util.List<org.bukkit.entity.Entity> getEntities();
    java.util.List<org.bukkit.entity.Entity> getNearbyEntities(Location location, double x, double y, double z);
    org.bukkit.block.Block getBlockAt(int x, int y, int z);
    org.bukkit.block.Block getBlockAt(Location location);
    org.bukkit.Chunk getChunkAt(int x, int z);
    org.bukkit.Chunk getChunkAt(Location location);
    org.bukkit.Chunk[] getLoadedChunks();

    <T> T getGameRuleValue(GameRule<T> rule);
    <T> T getGameRuleDefault(GameRule<T> rule);
    <T> boolean setGameRule(GameRule<T> rule, T value);
    boolean setGameRuleValue(String rule, String value);
    String getGameRuleValue(String rule);

    boolean setSpawnLocation(int x, int y, int z);
    boolean setSpawnLocation(int x, int y, int z, float angle);
    boolean setSpawnLocation(Location location);

    boolean unloadChunk(int x, int z, boolean save);
    boolean unloadChunk(int x, int z);
    boolean unloadChunk(org.bukkit.Chunk chunk);

    int getHighestBlockAt(int x, int z);
    int getHighestBlockAt(Location location);
    org.bukkit.block.Block getHighestBlockAt(int x, int z, org.bukkit.block.Biome biome);

    org.bukkit.boss.DragonBattle getEnderDragonBattle();

    <T extends org.bukkit.entity.Entity> java.util.Collection<T> getEntitiesByClass(Class<T> cls);
    <T extends org.bukkit.entity.Entity> java.util.Collection<T> getEntitiesByClasses(Class<T>... classes);

    int getChunkCount();
    int getEntityCount();
    int getTileEntityCount();
    String[] getGameRules();

    boolean isChunkGenerated(int x, int z);
    boolean isChunkLoaded(int x, int z);

    enum Environment {
        NORMAL,
        NETHER,
        THE_END,
        CUSTOM
    }

    @FunctionalInterface
    interface ChunkLoadCallback {
        void callback(Chunk chunk);
    }
}
