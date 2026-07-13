package me.wildmaster84.adapter.world;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import net.minestom.server.instance.block.Block;

public class SimpleChunkData implements ChunkData {
    private final int minHeight;
    private final int maxHeight;
    private final Map<Long, Block> blocks = new HashMap<>();

    public SimpleChunkData(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    private static long key(int x, int y, int z) {
        return ((long) y << 16) | ((x & 0xFF) << 8) | (z & 0xFF);
    }

    @Override
    public void setBlock(int x, int y, int z, Material material) {
        String blockKey = "minecraft:" + material.name().toLowerCase();
        Block msBlock = Block.fromKey(blockKey);
        if (msBlock != null) {
            blocks.put(key(x, y, z), msBlock);
        }
    }

    @Override
    public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Material material) {
        String blockKey = "minecraft:" + material.name().toLowerCase();
        Block msBlock = Block.fromKey(blockKey);
        if (msBlock == null) return;
        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                for (int z = zMin; z < zMax; z++) {
                    blocks.put(key(x, y, z), msBlock);
                }
            }
        }
    }

    @Override
    public Material getBlockData(int x, int y, int z) {
        Block msBlock = blocks.get(key(x, y, z));
        if (msBlock == null) return Material.AIR;
        String name = msBlock.name();
        String stripped = name.startsWith("minecraft:") ? name.substring(10) : name;
        Material mat = Material.matchMaterial(stripped);
        return mat != null ? mat : Material.AIR;
    }

    @Override
    public org.bukkit.block.Biome getBiome(int x, int y, int z) {
        return org.bukkit.block.Biome.PLAINS;
    }

    @Override
    public void setBiome(int x, int y, int z, org.bukkit.block.Biome biome) {
    }

    @Override
    public int getMinHeight() { return minHeight; }

    @Override
    public int getMaxHeight() { return maxHeight; }

    public void applyToUnit(net.minestom.server.instance.generator.GenerationUnit unit, int chunkX, int chunkZ) {
        if (blocks.isEmpty()) return;
        var modifier = unit.modifier();
        int baseX = chunkX * 16;
        int baseZ = chunkZ * 16;
        for (var entry : blocks.entrySet()) {
            long k = entry.getKey();
            int x = (int) ((k >> 8) & 0xFF);
            int y = (int) (k >> 16);
            int z = (int) (k & 0xFF);
            modifier.setBlock(baseX + x, y, baseZ + z, entry.getValue());
        }
    }

    public boolean hasBlocks() {
        return !blocks.isEmpty();
    }
}
