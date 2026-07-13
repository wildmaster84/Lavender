package me.wildmaster84.adapter.world;

import org.bukkit.World;
import org.bukkit.generator.WorldInfo;

public class SimpleWorldInfo implements WorldInfo {
    private final String name;
    private final long seed;
    private final World.Environment environment;
    private final int minHeight;
    private final int maxHeight;

    public SimpleWorldInfo(String name, long seed, World.Environment environment, int minHeight, int maxHeight) {
        this.name = name;
        this.seed = seed;
        this.environment = environment;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override public String getName() { return name; }
    @Override public long getSeed() { return seed; }
    @Override public World.Environment getEnvironment() { return environment; }
    @Override public int getMinHeight() { return minHeight; }
    @Override public int getMaxHeight() { return maxHeight; }
}
