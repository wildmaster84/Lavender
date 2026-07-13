package org.bukkit;

public interface Chunk {
    int getX();
    int getZ();
    World getWorld();
    org.bukkit.block.Block getBlock(int x, int y, int z);
    org.bukkit.entity.Entity[] getEntities();
    org.bukkit.entity.LivingEntity[] getLivingEntities();
    ChunkSnapshot getChunkSnapshot();
    boolean isLoaded();
    boolean load();
    boolean load(boolean generate);
    boolean unload();
    boolean unload(boolean save);
    boolean isGenerated();
    int getInhabitedTime();
    void setInhabitedTime(int ticks);
    boolean contains(org.bukkit.block.Block block);
    boolean isSlimeChunk();
    boolean isForceLoaded();
    void setForceLoaded(boolean forced);
    long getChunkKey();
    org.bukkit.block.BlockState[] getTileEntities();
}
