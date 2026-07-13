package org.bukkit;

public interface ChunkSnapshot {
    int getX();
    int getZ();
    String getWorldName();
    org.bukkit.Material getBlockType(int x, int y, int z);
    int getBlockData(int x, int y, int z);
    byte getBlockSkyLight(int x, int y, int z);
    byte getBlockEmittedLight(int x, int y, int z);
    int getHighestBlockYAt(int x, int z);
    org.bukkit.block.Biome getBiome(int x, int z);
    double getRawBiomeTemperature(int x, int z);
    double getRawBiomeHumidity(int x, int z);
    long getCaptureFullTime();
    boolean isSectionEmpty(int sy);
}