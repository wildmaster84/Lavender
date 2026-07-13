package org.bukkit;

import java.util.Random;

import org.bukkit.generator.ChunkGenerator;

public class WorldCreator {

    private final String name;
    private long seed;
    private World.Environment environment = World.Environment.NORMAL;
    private WorldType type = WorldType.NORMAL;
    private boolean generateStructures = true;
    private String generatorSettings = "";
    private boolean hardcore = false;
    private ChunkGenerator generator;
    private String generatorName;

    public WorldCreator(String name) {
        this.name = name;
        this.seed = new Random().nextLong();
    }

    public static WorldCreator name(String name) {
        return new WorldCreator(name);
    }

    public WorldCreator seed(long seed) {
        this.seed = seed;
        return this;
    }

    public WorldCreator environment(World.Environment environment) {
        this.environment = environment;
        return this;
    }

    public WorldCreator type(WorldType type) {
        this.type = type;
        return this;
    }

    public WorldCreator generateStructures(boolean generate) {
        this.generateStructures = generate;
        return this;
    }

    public WorldCreator generatorSettings(String settings) {
        this.generatorSettings = settings;
        return this;
    }

    public WorldCreator hardcore(boolean hardcore) {
        this.hardcore = hardcore;
        return this;
    }

    public WorldCreator generator(ChunkGenerator generator) {
        this.generator = generator;
        return this;
    }

    public WorldCreator generator(String generatorName) {
        this.generatorName = generatorName;
        return this;
    }

    public WorldCreator keepSpawnLoaded(net.kyori.adventure.util.TriState state) {
        return this;
    }

    public String name() { return name; }
    public long seed() { return seed; }
    public World.Environment environment() { return environment; }
    public WorldType type() { return type; }
    public boolean generateStructures() { return generateStructures; }
    public String generatorSettings() { return generatorSettings; }
    public boolean hardcore() { return hardcore; }
    public ChunkGenerator generator() { return generator; }
    public String generatorName() { return generatorName; }

    public World create() {
        return Bukkit.getServer().createWorld(this);
    }
}
