package me.wildmaster84.adapter.world;

import me.wildmaster84.adapter.server.LavenderServer;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.registry.RegistryData;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LavenderWorld extends org.bukkit.craftbukkit.CraftWorld {

    private static final int BLOCK_CACHE_MAX = 8192;
    private static final int CHUNK_CACHE_MAX = 1024;

    private final Instance instance;
    private final LavenderServer server;
    private final String name;
    private final long seed;
    private final Environment environment;
    private Location spawnLocation;
    private net.minecraft.server.level.ServerLevel serverLevel;
    private final ConcurrentHashMap<Long, LavenderBlock> blockCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, LavenderChunk> chunkCache = new ConcurrentHashMap<>();

    static long blockKey(int x, int y, int z) {
        return ((long)(x & 0x3FFFFFF) << 38) | ((long)(y & 0xFFF) << 26) | (z & 0x3FFFFFF);
    }

    static long chunkKey(int x, int z) {
        return ((long)x << 32) | (z & 0xFFFFFFFFL);
    }

    public LavenderWorld(Instance instance, LavenderServer server, String name, long seed, Environment environment) {
        this.instance = instance;
        this.server = server;
        this.name = name;
        this.seed = seed;
        this.environment = environment;
        this.spawnLocation = new Location(this, 0, 100, 0);
    }

    public LavenderServer getServer() { return server; }

    public Instance getInstance() { return instance; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof World)) return false;
        World other = (World) obj;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() { return getName().hashCode(); }

    @Override public String getName() { return name; }

    @Override
    public UUID getUID() {
        return instance.getUuid();
    }

    @Override
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z) {
        return setSpawnLocation(x, y, z, 0f);
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z, float angle) {
        spawnLocation = new Location(this, x, y, z, angle, 0f);
        return true;
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        if (location == null) return false;
        spawnLocation = new Location(this, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> getPlayers() {
        return (List<Player>) instance.getPlayers()
                .stream()
                .map(p -> server.getPlayer(p.getUuid()))
                .collect(Collectors.toList());
    }

    @Override public long getTime() { return instance.getTime(); }
    @Override public void setTime(long time) { instance.setTime(time); }
    @Override public long getFullTime() { return instance.getTime(); }
    @Override public void setFullTime(long time) { instance.setTime(time); }
    @Override public boolean hasStorm() { return false; }
    @Override public void setStorm(boolean hasStorm) {}
    @Override public boolean isDayTime() { return (instance.getTime() % 24000) < 12000; }
    @Override public int getMaxHeight() { return 320; }
    @Override public int getMinHeight() { return -64; }
    @Override public int getSeaLevel() { return 63; }
    @Override public long getSeed() { return seed; }
    @Override public boolean getPVP() { return server.getProperties().isPvp(); }
    @Override public void setPVP(boolean pvp) {}
    @Override public Difficulty getDifficulty() {
        try {
            return Difficulty.valueOf(server.getProperties().getDifficulty().toUpperCase());
        } catch (Exception e) {
            return Difficulty.NORMAL;
        }
    }
    @Override public void setDifficulty(Difficulty difficulty) {}
    @Override public Environment getEnvironment() { return environment; }
    private WorldBorder worldBorder;
    @Override public WorldBorder getWorldBorder() {
        if (worldBorder == null) worldBorder = new SimpleWorldBorder();
        return worldBorder;
    }

    @Override
    public void save() {
        try {
            instance.saveChunksToStorage().get(5, java.util.concurrent.TimeUnit.SECONDS);
        } catch (java.util.concurrent.TimeoutException e) {
        } catch (java.util.concurrent.ExecutionException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean autoSave = true;

    @Override
    public boolean isAutoSave() {
        return autoSave;
    }

    @Override
    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

    @Override
    public <T extends org.bukkit.entity.Entity> T spawn(Location location, Class<T> clazz) {
        return null; // TODO: entity spawning
    }

    @Override
    public org.bukkit.entity.Item dropItem(Location location, org.bukkit.inventory.ItemStack item) {
        return null; // TODO: item dropping
    }

    @Override
    public void playSound(Location location, org.bukkit.Sound sound, float volume, float pitch) {}
    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {}

    @Override
    public List<org.bukkit.entity.Entity> getEntities() {
        return instance.getEntities()
                .stream()
                .map(e -> (org.bukkit.entity.Entity) null) // TODO: wrap entities
                .collect(Collectors.toList());
    }

    @Override
    public List<org.bukkit.entity.Entity> getNearbyEntities(Location location, double x, double y, double z) {
        return java.util.Collections.emptyList();
    }

    @Override
    public org.bukkit.block.Block getBlockAt(int x, int y, int z) {
        long key = blockKey(x, y, z);
        LavenderBlock cached = blockCache.get(key);
        if (cached != null) return cached;
        if (blockCache.size() >= BLOCK_CACHE_MAX) blockCache.clear();
        LavenderBlock block = new LavenderBlock(instance, this, x, y, z);
        blockCache.put(key, block);
        return block;
    }

    @Override
    public org.bukkit.block.Block getBlockAt(Location location) {
        return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public org.bukkit.Chunk getChunkAt(int x, int z) {
        long key = chunkKey(x, z);
        LavenderChunk cached = chunkCache.get(key);
        if (cached != null) return cached;
        if (chunkCache.size() >= CHUNK_CACHE_MAX) chunkCache.clear();
        LavenderChunk chunk = new LavenderChunk(instance, this, x, z);
        chunkCache.put(key, chunk);
        return chunk;
    }

    @Override
    public org.bukkit.Chunk getChunkAt(Location location) {
        return getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    @Override
    public org.bukkit.Chunk[] getLoadedChunks() {
        java.util.Collection<Chunk> chunks = instance.getChunks();
        org.bukkit.Chunk[] result = new org.bukkit.Chunk[chunks.size()];
        int i = 0;
        for (Chunk msChunk : chunks) {
            result[i++] = getChunkAt(msChunk.getChunkX(), msChunk.getChunkZ());
        }
        return result;
    }

    private final java.util.Map<org.bukkit.GameRule<?>, Object> gameRules = new java.util.concurrent.ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getGameRuleValue(org.bukkit.GameRule<T> rule) {
        return (T) gameRules.getOrDefault(rule, getDefaultGameRuleValue(rule));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getGameRuleDefault(org.bukkit.GameRule<T> rule) {
        return getDefaultGameRuleValue(rule);
    }

    @Override
    public <T> boolean setGameRule(org.bukkit.GameRule<T> rule, T value) {
        gameRules.put(rule, value);
        return true;
    }

    @Override
    public boolean setGameRuleValue(String rule, String value) {
        org.bukkit.GameRule<?> gr = org.bukkit.GameRule.getByName(rule);
        if (gr == null) return false;
        if (gr.getType() == Boolean.class) {
            gameRules.put(gr, Boolean.parseBoolean(value));
        } else if (gr.getType() == Integer.class) {
            gameRules.put(gr, Integer.parseInt(value));
        } else {
            gameRules.put(gr, value);
        }
        return true;
    }

    @Override
    public String getGameRuleValue(String rule) {
        org.bukkit.GameRule<?> gr = org.bukkit.GameRule.getByName(rule);
        if (gr == null) return null;
        Object val = gameRules.get(gr);
        return val != null ? val.toString() : null;
    }

    @SuppressWarnings("unchecked")
    private <T> T getDefaultGameRuleValue(org.bukkit.GameRule<T> rule) {
        if (rule.getType() == Boolean.class) {
            if (rule == org.bukkit.GameRule.DO_FIRE_TICK
                    || rule == org.bukkit.GameRule.DO_MOB_SPAWNING
                    || rule == org.bukkit.GameRule.DO_MOB_LOOT
                    || rule == org.bukkit.GameRule.DO_TILE_DROPS
                    || rule == org.bukkit.GameRule.DO_ENTITY_DROPS
                    || rule == org.bukkit.GameRule.DO_DAYLIGHT_CYCLE
                    || rule == org.bukkit.GameRule.DO_WEATHER_CYCLE
                    || rule == org.bukkit.GameRule.MOB_GRIEFING
                    || rule == org.bukkit.GameRule.ANNOUNCE_ADVANCEMENTS
                    || rule == org.bukkit.GameRule.LOG_ADMIN_COMMANDS
                    || rule == org.bukkit.GameRule.SEND_COMMAND_FEEDBACK
                    || rule == org.bukkit.GameRule.NATURAL_REGENERATION
                    || rule == org.bukkit.GameRule.SHOW_DEATH_MESSAGES
                    || rule == org.bukkit.GameRule.SPECTATORS_GENERATE_CHUNKS
                    || rule == org.bukkit.GameRule.GLOBAL_SOUND_EVENTS
                    || rule == org.bukkit.GameRule.DO_VINES_SPREAD) {
                return (T) Boolean.TRUE;
            }
            return (T) Boolean.FALSE;
        }
        if (rule.getType() == Integer.class) {
            if (rule == org.bukkit.GameRule.RANDOM_TICK_SPEED) return (T) Integer.valueOf(3);
            if (rule == org.bukkit.GameRule.SPAWN_RADIUS) return (T) Integer.valueOf(10);
            if (rule == org.bukkit.GameRule.MAX_COMMAND_CHAIN_LENGTH) return (T) Integer.valueOf(65536);
            if (rule == org.bukkit.GameRule.MAX_ENTITY_CRAMMING_INT) return (T) Integer.valueOf(24);
            if (rule == org.bukkit.GameRule.COMMAND_MODIFICATION_BLOCK_LIMIT) return (T) Integer.valueOf(32768);
            return (T) Integer.valueOf(0);
        }
        return null;
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save) {
        if (save) {
            Chunk chunk = instance.getChunk(x, z);
            if (chunk != null) {
                try {
                    instance.saveChunkToStorage(chunk).get();
                } catch (Exception e) {}
            }
        }
        instance.unloadChunk(x, z);
        return true;
    }

    @Override
    public boolean unloadChunk(int x, int z) {
        return unloadChunk(x, z, true);
    }

    @Override
    public boolean unloadChunk(org.bukkit.Chunk chunk) {
        return unloadChunk(chunk.getX(), chunk.getZ(), true);
    }

    @Override
    public int getHighestBlockAt(int x, int z) {
        if (!instance.isChunkLoaded(x >> 4, z >> 4)) return -64;
        for (int y = getMaxHeight() - 1; y >= -64; y--) {
            Block block = instance.getBlock(x, y, z);
            if (block != null && !block.isAir() && !block.isLiquid()) {
                return y;
            }
        }
        return -64;
    }

    @Override
    public int getHighestBlockAt(Location location) {
        return getHighestBlockAt(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public org.bukkit.block.Block getHighestBlockAt(int x, int z, org.bukkit.block.Biome biome) {
        return getBlockAt(x, getHighestBlockAt(x, z), z);
    }

    @Override
    public org.bukkit.boss.DragonBattle getEnderDragonBattle() {
        return null; // TODO: please no
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends org.bukkit.entity.Entity> java.util.Collection<T> getEntitiesByClass(Class<T> cls) {
        java.util.List<T> result = new java.util.ArrayList<>();
        for (net.minestom.server.entity.Entity entity : instance.getEntities()) {
            // TODO: wrap entities and check class
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends org.bukkit.entity.Entity> java.util.Collection<T> getEntitiesByClasses(Class<T>... classes) {
        java.util.List<T> result = new java.util.ArrayList<>();
        for (net.minestom.server.entity.Entity entity : instance.getEntities()) {
            // TODO: wrap entities and check classes
        }
        return result;
    }

    @Override
    public int getChunkCount() {
        return instance.getChunks().size();
    }

    @Override
    public int getEntityCount() {
        return instance.getEntities().size();
    }

    @Override
    public int getTileEntityCount() {
        int count = 0;
        for (Chunk chunk : instance.getChunks()) {
            int minSection = chunk.getMinSection();
            int maxSection = chunk.getMaxSection();
            int baseX = chunk.getChunkX() * 16;
            int baseZ = chunk.getChunkZ() * 16;
            for (int section = minSection; section < maxSection; section++) {
                int baseY = section * 16;
                for (int bx = 0; bx < 16; bx++) {
                    for (int bz = 0; bz < 16; bz++) {
                        for (int by = 0; by < 16; by++) {
                            try {
                                Block msBlock = instance.getBlock(baseX + bx, baseY + by, baseZ + bz);
                                if (msBlock != null && !msBlock.isAir()) {
                                    RegistryData.BlockEntry registry = msBlock.registry();
                                    if (registry != null && registry.isBlockEntity()) {
                                        count++;
                                    }
                                }
                            } catch (Exception e) {}
                        }
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String[] getGameRules() {
        return gameRules.keySet().stream()
                .map(org.bukkit.GameRule::getName)
                .toArray(String[]::new);
    }

    @Override
    public boolean isChunkGenerated(int x, int z) {
        if (instance.isChunkLoaded(x, z)) return true;
        try {
            Chunk chunk = instance.getChunk(x, z);
            if (chunk != null) return true;
        } catch (Exception e) {
            // ignore
        }
        File worldDir = new File("world" + File.separator + "dimensions" + File.separator + "minecraft" + File.separator + name);
        int regionX = x >> 5;
        int regionZ = z >> 5;
        File regionFile = new File(worldDir, "region" + File.separator + "r." + regionX + "." + regionZ + ".mca");
        if (regionFile.exists()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return instance.isChunkLoaded(x, z);
    }

    @Override
    public net.minecraft.server.level.ServerLevel getHandle() {
        if (serverLevel == null) {
            serverLevel = new net.minecraft.server.level.ServerLevel(instance, this);
        }
        return serverLevel;
    }
}
