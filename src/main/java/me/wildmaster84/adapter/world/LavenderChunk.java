package me.wildmaster84.adapter.world;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import me.wildmaster84.adapter.entity.LavenderEntity;
import me.wildmaster84.adapter.player.LavenderPlayer;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.Chunk;
import net.minestom.server.registry.RegistryData;

public class LavenderChunk implements org.bukkit.Chunk {
    private final Instance instance;
    private final LavenderWorld world;
    private final int x, z;

    public LavenderChunk(Instance instance, LavenderWorld world, int x, int z) {
        this.instance = instance;
        this.world = world;
        this.x = x;
        this.z = z;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        int worldX = this.x * 16 + x;
        int worldZ = this.z * 16 + z;
        return new LavenderBlock(instance, world, worldX, y, worldZ);
    }

    @Override
    public Entity[] getEntities() {
        Chunk chunk = instance.getChunk(x, z);
        if (chunk == null) return new Entity[0];
        java.util.Set<net.minestom.server.entity.Entity> msEntities = instance.getChunkEntities(chunk);
        Entity[] result = new Entity[msEntities.size()];
        int i = 0;
        for (net.minestom.server.entity.Entity msEntity : msEntities) {
            if (msEntity instanceof net.minestom.server.entity.Player mp) {
                result[i++] = LavenderPlayer.wrap(mp, world.getServer());
            } else {
                result[i++] = new LavenderEntity(msEntity, world.getServer());
            }
        }
        return i == msEntities.size() ? result : java.util.Arrays.copyOf(result, i);
    }

    @Override
    public LivingEntity[] getLivingEntities() {
        return new LivingEntity[0];
    }

    @Override
    public org.bukkit.ChunkSnapshot getChunkSnapshot() {
        return null;
    }

    @Override
    public boolean isLoaded() {
        return instance.isChunkLoaded(x, z);
    }

    @Override
    public boolean load() {
        if (instance.isChunkLoaded(x, z)) return true;
        try {
            CompletableFuture<Chunk> future = instance.loadChunk(x, z);
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(boolean generate) {
        if (instance.isChunkLoaded(x, z)) return true;
        if (generate) {
            try {
                CompletableFuture<Chunk> future = instance.loadOptionalChunk(x, z);
                future.get();
                return true;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return false;
        }
        return load();
    }

    @Override
    public boolean unload() {
        instance.unloadChunk(x, z);
        return true;
    }

    @Override
    public boolean unload(boolean save) {
        if (save) {
            Chunk chunk = instance.getChunk(x, z);
            if (chunk != null) {
                try {
                    instance.saveChunkToStorage(chunk).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        instance.unloadChunk(x, z);
        return true;
    }

    @Override
    public boolean isGenerated() {
        Chunk chunk = instance.getChunk(x, z);
        return chunk != null && chunk.isLoaded();
    }

    @Override
    public int getInhabitedTime() {
        return 0;
    }

    @Override
    public void setInhabitedTime(int ticks) {
    }

    @Override
    public boolean contains(Block block) {
        return block.getX() >> 4 == x && block.getZ() >> 4 == z;
    }

    @Override
    public boolean isSlimeChunk() {
        return false;
    }

    @Override
    public boolean isForceLoaded() {
        return false;
    }

    @Override
    public void setForceLoaded(boolean forced) {
    }

    @Override
    public long getChunkKey() {
        return (long) x << 32 | z & 0xFFFFFFFFL;
    }

    @Override
    public org.bukkit.block.BlockState[] getTileEntities() {
        Chunk chunk = instance.getChunk(x, z);
        if (chunk == null) return new org.bukkit.block.BlockState[0];

        java.util.List<org.bukkit.block.BlockState> result = new java.util.ArrayList<>();
        int minSection = chunk.getMinSection();
        int maxSection = chunk.getMaxSection();
        int baseX = x * 16;
        int baseZ = z * 16;

        for (int section = minSection; section < maxSection; section++) {
            int baseY = section * 16;
            for (int bx = 0; bx < 16; bx++) {
                for (int bz = 0; bz < 16; bz++) {
                    for (int by = 0; by < 16; by++) {
                        int worldX = baseX + bx;
                        int worldY = baseY + by;
                        int worldZ = baseZ + bz;
                        try {
                            net.minestom.server.instance.block.Block msBlock = instance.getBlock(worldX, worldY, worldZ);
                            if (msBlock != null && !msBlock.isAir()) {
                                RegistryData.BlockEntry registry = msBlock.registry();
                                if (registry != null && registry.isBlockEntity()) {
                                    result.add(new LavenderBlockState(instance, world, worldX, worldY, worldZ));
                                }
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        }
        return result.toArray(new org.bukkit.block.BlockState[0]);
    }
}
