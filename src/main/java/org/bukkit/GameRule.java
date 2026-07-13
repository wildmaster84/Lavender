package org.bukkit;

public final class GameRule<T> {

    public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENTS = new GameRule<>("announceAdvancements", Boolean.class);
    public static final GameRule<Boolean> COMMAND_BLOCK_OUTPUT = new GameRule<>("commandBlockOutput", Boolean.class);
    public static final GameRule<Boolean> DISABLE_ELYTRA_MOVEMENT_CHECK = new GameRule<>("disableElytraMovementCheck", Boolean.class);
    public static final GameRule<Boolean> DISABLE_RAIDS = new GameRule<>("disableRaids", Boolean.class);
    public static final GameRule<Boolean> DO_DAYLIGHT_CYCLE = new GameRule<>("doDaylightCycle", Boolean.class);
    public static final GameRule<Boolean> DO_ENTITY_DROPS = new GameRule<>("doEntityDrops", Boolean.class);
    public static final GameRule<Boolean> DO_FIRE_TICK = new GameRule<>("doFireTick", Boolean.class);
    public static final GameRule<Boolean> DO_INSOMNIA = new GameRule<>("doInsomnia", Boolean.class);
    public static final GameRule<Boolean> DO_IMMEDIATE_RESPAWN = new GameRule<>("doImmediateRespawn", Boolean.class);
    public static final GameRule<Boolean> DO_LIMITED_CRAFTING = new GameRule<>("doLimitedCrafting", Boolean.class);
    public static final GameRule<Boolean> DO_MOB_LOOT = new GameRule<>("doMobLoot", Boolean.class);
    public static final GameRule<Boolean> DO_MOB_SPAWNING = new GameRule<>("doMobSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_PATROL_SPAWNING = new GameRule<>("doPatrolSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_TILE_DROPS = new GameRule<>("doTileDrops", Boolean.class);
    public static final GameRule<Boolean> DO_TRADER_SPAWNING = new GameRule<>("doTraderSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_WARDEN_SPAWNING = new GameRule<>("doWardenSpawning", Boolean.class);
    public static final GameRule<Boolean> DO_WEATHER_CYCLE = new GameRule<>("doWeatherCycle", Boolean.class);
    public static final GameRule<Boolean> DROWNING_DAMAGE = new GameRule<>("drowningDamage", Boolean.class);
    public static final GameRule<Boolean> FALL_DAMAGE = new GameRule<>("fallDamage", Boolean.class);
    public static final GameRule<Boolean> FIRE_DAMAGE = new GameRule<>("fireDamage", Boolean.class);
    public static final GameRule<Boolean> FORGIVE_DEAD_PLAYERS = new GameRule<>("forgiveDeadPlayers", Boolean.class);
    public static final GameRule<Boolean> KEEP_INVENTORY = new GameRule<>("keepInventory", Boolean.class);
    public static final GameRule<Boolean> LOG_ADMIN_COMMANDS = new GameRule<>("logAdminCommands", Boolean.class);
    public static final GameRule<Boolean> MOB_GRIEFING = new GameRule<>("mobGriefing", Boolean.class);
    public static final GameRule<Boolean> NATURAL_REGENERATION = new GameRule<>("naturalRegeneration", Boolean.class);
    public static final GameRule<Boolean> REDUCED_DEBUG_INFO = new GameRule<>("reducedDebugInfo", Boolean.class);
    public static final GameRule<Boolean> SEND_COMMAND_FEEDBACK = new GameRule<>("sendCommandFeedback", Boolean.class);
    public static final GameRule<Boolean> SHOW_DEATH_MESSAGES = new GameRule<>("showDeathMessages", Boolean.class);
    public static final GameRule<Boolean> SPECTATORS_GENERATE_CHUNKS = new GameRule<>("spectatorsGenerateChunks", Boolean.class);
    public static final GameRule<Boolean> UNIVERSAL_ANGER = new GameRule<>("universalAnger", Boolean.class);
    public static final GameRule<Boolean> DO_WARDEN_SPAWNING_TICK = new GameRule<>("doWardenSpawning", Boolean.class);
    public static final GameRule<Boolean> PLAYERS_SLEEPING_PERCENTAGE = new GameRule<>("playersSleepingPercentage", Boolean.class);
    public static final GameRule<Boolean> DO_IMMEDIATE_RESPAWN_TICK = new GameRule<>("doImmediateRespawn", Boolean.class);
    public static final GameRule<Boolean> BLOCK_EXPLOSION_DROP_DECAY = new GameRule<>("blockExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> MOB_EXPLOSION_DROP_DECAY = new GameRule<>("mobExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> TNT_EXPLOSION_DROP_DECAY = new GameRule<>("tntExplosionDropDecay", Boolean.class);
    public static final GameRule<Boolean> GLOBAL_SOUND_EVENTS = new GameRule<>("globalSoundEvents", Boolean.class);
    public static final GameRule<Boolean> DO_VINES_SPREAD = new GameRule<>("doVinesSpread", Boolean.class);
    public static final GameRule<Boolean> PLAYERS_SLEEPING_PERCENTAGE_BOOL = new GameRule<>("playersSleepingPercentage", Boolean.class);
    public static final GameRule<Boolean> MAX_ENTITY_CRAMMING = new GameRule<>("maxEntityCramming", Boolean.class);
    public static final GameRule<Boolean> SNOW_ACCUMULATION_HEIGHT = new GameRule<>("snowAccumulationHeight", Boolean.class);
    public static final GameRule<Boolean> WATER_ENTITY_CRAMMING = new GameRule<>("waterEntityCramming", Boolean.class);

    public static final GameRule<Integer> MAX_COMMAND_CHAIN_LENGTH = new GameRule<>("maxCommandChainLength", Integer.class);
    public static final GameRule<Integer> RANDOM_TICK_SPEED = new GameRule<>("randomTickSpeed", Integer.class);
    public static final GameRule<Integer> SPAWN_RADIUS = new GameRule<>("spawnRadius", Integer.class);
    public static final GameRule<Integer> MAX_ENTITY_CRAMMING_INT = new GameRule<>("maxEntityCramming", Integer.class);
    public static final GameRule<Integer> COMMAND_MODIFICATION_BLOCK_LIMIT = new GameRule<>("commandModificationBlockLimit", Integer.class);

    private final String name;
    private final Class<T> type;

    private GameRule(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public Class<T> getType() { return type; }

    @SuppressWarnings("unchecked")
    public static GameRule<?> getByName(String name) {
        for (java.lang.reflect.Field field : GameRule.class.getDeclaredFields()) {
            try {
                if (field.getName().equalsIgnoreCase(name) || ((GameRule<?>) field.get(null)).name.equalsIgnoreCase(name)) {
                    return (GameRule<?>) field.get(null);
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    public static GameRule<?>[] values() {
        return new GameRule<?>[] {
            ANNOUNCE_ADVANCEMENTS, COMMAND_BLOCK_OUTPUT, DISABLE_ELYTRA_MOVEMENT_CHECK, DISABLE_RAIDS,
            DO_DAYLIGHT_CYCLE, DO_ENTITY_DROPS, DO_FIRE_TICK, DO_INSOMNIA, DO_IMMEDIATE_RESPAWN,
            DO_LIMITED_CRAFTING, DO_MOB_LOOT, DO_MOB_SPAWNING, DO_PATROL_SPAWNING, DO_TILE_DROPS,
            DO_TRADER_SPAWNING, DO_WARDEN_SPAWNING, DO_WEATHER_CYCLE, DROWNING_DAMAGE, FALL_DAMAGE,
            FIRE_DAMAGE, FORGIVE_DEAD_PLAYERS, KEEP_INVENTORY, LOG_ADMIN_COMMANDS, MOB_GRIEFING,
            NATURAL_REGENERATION, REDUCED_DEBUG_INFO, SEND_COMMAND_FEEDBACK, SHOW_DEATH_MESSAGES,
            SPECTATORS_GENERATE_CHUNKS, UNIVERSAL_ANGER, MAX_COMMAND_CHAIN_LENGTH, RANDOM_TICK_SPEED,
            SPAWN_RADIUS
        };
    }
}
