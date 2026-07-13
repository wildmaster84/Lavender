package org.bukkit.generator;

import org.bukkit.World;

public interface WorldInfo {
    String getName();
    long getSeed();
    World.Environment getEnvironment();
    int getMinHeight();
    int getMaxHeight();
}
