package org.bukkit.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;

import java.util.List;
import java.util.Random;

public abstract class ChunkGenerator {

    public interface ChunkData {
        void setBlock(int x, int y, int z, Material material);
        void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Material material);
        Material getBlockData(int x, int y, int z);
        org.bukkit.block.Biome getBiome(int x, int y, int z);
        void setBiome(int x, int y, int z, org.bukkit.block.Biome biome);
        int getMinHeight();
        int getMaxHeight();
    }

    public interface BiomeGrid {
        Biome getBiome(int x, int z);
        void setBiome(int x, int z, Biome biome);
        Biome getBiome(int x, int y, int z);
        void setBiome(int x, int y, int z, Biome biome);
    }

    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biomeGrid) {
        return null;
    }

    public void generateSurface(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {}
    public void generateBedrock(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {}
    public void generateCaves(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {}
    public void generateNoise(WorldInfo worldInfo, Random random, int x, int z, ChunkData chunkData) {}

    public boolean shouldGenerateSurface() { return false; }
    public boolean shouldGenerateStructures() { return false; }
    public boolean shouldGenerateMobs() { return false; }
    public boolean shouldGenerateDecorations() { return false; }
    public boolean shouldGenerateNoise() { return false; }
    public boolean shouldGenerateCaves() { return false; }

    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) { return null; }

    public boolean canSpawn(World world, int x, int z) { return true; }
    public List<BlockPopulator> getDefaultPopulators(World world) { return java.util.Collections.emptyList(); }
    public org.bukkit.Location getFixedSpawnLocation(World world, Random random) { return null; }
    public boolean isParallelCapable() { return true; }
}
