package me.wildmaster84.adapter.world;

import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;

public class LavenderBlock implements org.bukkit.block.Block {
    private final Instance instance;
    private final LavenderWorld world;
    private final int x, y, z;

    public LavenderBlock(Instance instance, LavenderWorld world, int x, int y, int z) {
        this.instance = instance;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private boolean isChunkLoaded() {
        return instance.isChunkLoaded(x >> 4, z >> 4);
    }

    @Override
    public World getWorld() { return world; }

    @Override
    public Location getLocation() { return new Location(world, x, y, z); }

    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }
    @Override
    public int getZ() { return z; }

    @Override
    public Material getType() {
        if (!isChunkLoaded()) return Material.AIR;
        Block msBlock = instance.getBlock(x, y, z);
        if (msBlock == null) return Material.AIR;
        String name = msBlock.name();
        String stripped = name.startsWith("minecraft:") ? name.substring(10) : name;
        Material mat = Material.matchMaterial(stripped);
        return mat != null ? mat : Material.AIR;
    }

    @Override
    public void setType(Material type) {
        setType(type, true);
    }

    @Override
    public void setType(Material type, boolean applyPhysics) {
        if (!isChunkLoaded()) return;
        String key = "minecraft:" + type.name().toLowerCase();
        try {
            Block msBlock = Block.fromKey(key);
            if (msBlock != null) {
                int cx = x >> 4;
                int cz = z >> 4;
                net.minestom.server.instance.Chunk chunk = instance.getChunk(cx, cz);
                if (chunk != null) {
                    chunk.setBlock(x, y, z, msBlock);
                    net.minecraft.world.level.chunk.DirtyChunkTracker.markDirty(instance, cx, cz);
                }
            }
        } catch (Exception e) {}
    }

    @Override
    public byte getData() { return 0; }

    @Override
    public void setData(byte data) {}

    @Override
    public org.bukkit.block.Block getRelative(int modX, int modY, int modZ) {
        return new LavenderBlock(instance, world, x + modX, y + modY, z + modZ);
    }

    @Override
    public org.bukkit.block.Block getRelative(org.bukkit.block.BlockFace face) {
        return getRelative(face.getModX(), face.getModY(), face.getModZ());
    }

    @Override
    public org.bukkit.block.Block getRelative(org.bukkit.block.BlockFace face, int distance) {
        return getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
    }

    @Override
    public org.bukkit.block.BlockState getState() {
        return new LavenderBlockState(instance, world, x, y, z);
    }

    @Override
    public org.bukkit.block.Biome getBiome() { return org.bukkit.block.Biome.PLAINS; }

    @Override
    public void setBiome(org.bukkit.block.Biome bio) {}

    @Override
    public boolean isLiquid() {
        if (!isChunkLoaded()) return false;
        Block msBlock = instance.getBlock(x, y, z);
        if (msBlock == null) return false;
        String name = msBlock.name();
        return name.contains("water") || name.contains("lava");
    }

    @Override
    public boolean isEmpty() {
        if (!isChunkLoaded()) return true;
        Block msBlock = instance.getBlock(x, y, z);
        return msBlock == null || msBlock.isAir();
    }

    @Override
    public boolean isSolid() {
        if (!isChunkLoaded()) return false;
        Block msBlock = instance.getBlock(x, y, z);
        if (msBlock == null || msBlock.isAir()) return false;
        return msBlock.registry().isSolid();
    }

    @Override
    public double getTemperature() { return 0.5; }
    @Override
    public double getHumidity() { return 0.5; }
    @Override
    public boolean isPowered() { return false; }
    @Override
    public boolean isBlockPowered() { return false; }
    @Override
    public boolean isBlockIndirectlyPowered() { return false; }
    @Override
    public boolean isBlockFacePowered(BlockFace face) { return false; }
    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) { return false; }
    @Override
    public int getBlockPower() { return 0; }
    @Override
    public int getBlockPower(BlockFace face) { return 0; }
    @Override
    public boolean breakNaturally() { return false; }
    @Override
    public boolean applyBoneMeal(BlockFace face) { return false; }
    @Override
    public boolean canPlace() { return true; }

    @Override
    public void setBlockData(org.bukkit.block.data.BlockData blockData) {
        setBlockData(blockData, true);
    }

    @Override
    public void setBlockData(org.bukkit.block.data.BlockData blockData, boolean applyPhysics) {
        if (blockData == null) return;
        setType(blockData.getMaterial(), applyPhysics);
    }

    @Override
    public org.bukkit.block.data.BlockData getBlockData() {
        return new SimpleBlockData(getType());
    }

    @Override
    public org.bukkit.Chunk getChunk() { return world.getChunkAt(x >> 4, z >> 4); }
}
