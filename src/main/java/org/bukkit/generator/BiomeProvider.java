package org.bukkit.generator;

import org.bukkit.block.Biome;

import java.util.List;

public abstract class BiomeProvider {

    public abstract Biome getBiome(org.bukkit.World world, int x, int y, int z);

    public List<Biome> getBiomes(org.bukkit.World world) {
        return List.of();
    }
}
