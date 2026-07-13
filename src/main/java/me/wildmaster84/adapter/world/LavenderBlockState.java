package me.wildmaster84.adapter.world;

import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class LavenderBlockState implements org.bukkit.block.BlockState {

    private final Instance instance;
    private final LavenderWorld world;
    private final int x, y, z;

    public LavenderBlockState(Instance instance, LavenderWorld world, int x, int y, int z) {
        this.instance = instance;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Location getLocation() {
        return new Location(world, x, y, z);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Material getType() {
        if (!instance.isChunkLoaded(x >> 4, z >> 4)) return Material.AIR;
        Block msBlock = instance.getBlock(x, y, z);
        if (msBlock == null) return Material.AIR;
        String name = msBlock.name();
        String stripped = name.startsWith("minecraft:") ? name.substring(10) : name;
        Material mat = Material.matchMaterial(stripped);
        return mat != null ? mat : Material.AIR;
    }

    @Override
    public void setType(Material type) {
        if (!instance.isChunkLoaded(x >> 4, z >> 4)) return;
        String key = "minecraft:" + type.name().toLowerCase();
        try {
            Block msBlock = Block.fromKey(key);
            if (msBlock != null) {
                instance.setBlock(x, y, z, msBlock);
            }
        } catch (Exception e) {}
    }

    @Override
    public byte getData() {
        return new LavenderBlock(instance, world, x, y, z).getData();
    }

    @Override
    public org.bukkit.block.Block getBlock() {
        return new LavenderBlock(instance, world, x, y, z);
    }

    @Override
    public boolean update() {
        return update(false);
    }

    @Override
    public boolean update(boolean force) {
        return true;
    }

    @Override
    public org.bukkit.block.data.BlockData getBlockData() {
        return null;
    }

    @Override
    public void setBlockData(org.bukkit.block.data.BlockData blockData) {
    }
}
