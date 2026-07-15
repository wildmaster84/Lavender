package net.minecraft.server.level;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.SavedDataStorage;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;

import io.papermc.paper.world.PaperWorldLoader;
import io.papermc.paper.world.saveddata.PaperLevelOverrides;

public class ServerLevel extends Level implements WorldGenLevel {
    public PaperLevelOverrides serverLevelData;
    public WorldGenSettings worldGenSettings;

    private final net.minestom.server.instance.Instance minestomInstance;
    private final MinecraftServer server;
    private final ServerChunkCache chunkSource;
    private final ResourceKey<?> dimension;
    private final long seed;
    private final CraftWorld craftWorld;

    public ServerLevel(
            MinecraftServer server,
            Executor executor,
            LevelStorageSource.LevelStorageAccess access,
            WorldGenSettings worldGenSettings,
            ResourceKey<?> dimension,
            LevelStem stem,
            boolean debug,
            long seed,
            List<?> list,
            boolean flag,
            ResourceKey<?> dimensionKey,
            org.bukkit.World.Environment environment,
            ChunkGenerator chunkGenerator,
            BiomeProvider biomeProvider,
            SavedDataStorage savedDataStorage,
            PaperWorldLoader.LoadedWorldData loadedWorldData
    ) {
        this.server = server;
        this.worldGenSettings = worldGenSettings;
        this.dimension = dimension;
        this.seed = seed;
        this.minestomInstance = null;
        this.craftWorld = null;
        this.chunkSource = new ServerChunkCache();
        this.serverLevelData = new PaperLevelOverrides();
    }

    public ServerLevel(net.minestom.server.instance.Instance minestomInstance) {
        this(minestomInstance, null);
    }

    public ServerLevel(net.minestom.server.instance.Instance minestomInstance, CraftWorld craftWorld) {
        this.minestomInstance = minestomInstance;
        this.server = net.minecraft.server.dedicated.DedicatedServer.getServer();
        this.craftWorld = craftWorld;
        this.chunkSource = new ServerChunkCache(minestomInstance);
        this.dimension = null;
        this.seed = 0;
        this.worldGenSettings = new WorldGenSettings();
        this.serverLevelData = new PaperLevelOverrides();
    }

    public net.minestom.server.instance.Instance getMinestomInstance() {
        return minestomInstance;
    }

    public LevelChunk getChunk(int x, int z) {
        if (minestomInstance == null) return new LevelChunk();
        if (!minestomInstance.isChunkLoaded(x, z)) return null;
        return new LevelChunk(minestomInstance, x, z);
    }

    public LevelChunk getChunkNow(int x, int z) {
        if (minestomInstance == null) return null;
        if (!minestomInstance.isChunkLoaded(x, z)) return null;
        return new LevelChunk(minestomInstance, x, z);
    }

    public ChunkAccess getChunk(int x, int z, ChunkStatus status, boolean load) {
        if (minestomInstance == null) return new LevelChunk();
        if (!load && !minestomInstance.isChunkLoaded(x, z)) return null;
        return new LevelChunk(minestomInstance, x, z);
    }

    public BlockState getBlockState(BlockPos pos) {
        if (minestomInstance == null) return new BlockState();
        net.minestom.server.instance.block.Block msBlock = minestomInstance.getBlock(pos.getX(), pos.getY(), pos.getZ());
        if (msBlock == null) msBlock = net.minestom.server.instance.block.Block.AIR;
        return new BlockState(Block.of(msBlock.defaultState()), msBlock);
    }

    public boolean setBlock(BlockPos pos, BlockState state, int flags) {
        if (minestomInstance == null) return false;
        int cx = pos.getX() >> 4;
        int cz = pos.getZ() >> 4;
        net.minestom.server.instance.Chunk chunk = minestomInstance.getChunk(cx, cz);
        if (chunk != null) {
            chunk.setBlock(pos.getX(), pos.getY(), pos.getZ(), state.getMinestomBlock());
            net.minecraft.world.level.chunk.DirtyChunkTracker.markDirty(minestomInstance, cx, cz);
        }
        return true;
    }

    public void flushDirtyChunks() {
        if (minestomInstance == null) return;
        Set<Long> dirty = net.minecraft.world.level.chunk.DirtyChunkTracker.drain(minestomInstance);
        if (dirty.isEmpty()) return;
        for (Long key : dirty) {
            int cx = (int) (key >> 32);
            int cz = (int) (key & 0xFFFFFFFFL);
            net.minestom.server.instance.Chunk chunk = minestomInstance.getChunk(cx, cz);
            if (chunk != null) {
                chunk.sendPacketToViewers(chunk.getFullDataPacket());
            }
        }
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        if (minestomInstance == null) return null;
        net.minestom.server.instance.block.Block msBlock = minestomInstance.getBlock(pos.getX(), pos.getY(), pos.getZ());
        if (msBlock == null || !msBlock.registry().isBlockEntity()) return null;
        BlockEntity be = new BlockEntity(pos, new BlockState(Block.of(msBlock.defaultState()), msBlock));
        be.setLevel(this);
        return be;
    }

    public ServerChunkCache getChunkSource() {
        return chunkSource;
    }

    public RegistryAccess registryAccess() {
        if (server != null) {
            return server.registryAccess();
        }
        return new me.wildmaster84.adapter.registry.LavenderRegistryAccess();
    }

    public MinecraftServer getServer() {
        return server;
    }

    public CraftWorld getWorld() {
        return craftWorld;
    }

    public CraftServer getCraftServer() {
        org.bukkit.Server bukkitServer = org.bukkit.Bukkit.getServer();
        if (bukkitServer instanceof CraftServer cs) return cs;
        return null;
    }

    public StructureManager structureManager() {
        return new StructureManager();
    }

    public StructureTemplateManager getStructureManager() {
        return new StructureTemplateManager();
    }

    public RandomSource getRandom() {
        return net.minecraft.util.RandomSource.create();
    }

    public int getMinY() {
        return minestomInstance != null ? minestomInstance.getCachedDimensionType().minY() : -64;
    }

    public int getMaxY() {
        return minestomInstance != null ? minestomInstance.getCachedDimensionType().maxY() : 319;
    }

    public int getSectionIndex(int y) {
        return Math.floorDiv(y - getMinY(), 16);
    }

    public void sendBlockUpdated(BlockPos pos, BlockState oldState, BlockState newState, int flags) {
        if (minestomInstance == null) return;
        net.minestom.server.instance.block.Block msBlock = newState != null ? newState.getMinestomBlock() : net.minestom.server.instance.block.Block.AIR;
        minestomInstance.setBlock(pos.getX(), pos.getY(), pos.getZ(), msBlock);
    }

    public void updateNeighborsAt(BlockPos pos, Block block) {
    }

    public void updateNeighbourForOutputSignal(BlockPos pos, Block block) {
    }

    public void updatePOIOnBlockStateChange(BlockPos pos, BlockState oldState, BlockState newState) {
    }

    public void addFreshEntityWithPassengers(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        if (minestomInstance == null) return;
        net.minestom.server.entity.Entity msEntity = entity.getMinestomEntity();
        msEntity.setInstance(minestomInstance);
    }

    public ResourceKey<?> dimension() {
        return dimension;
    }

    public Holder<?> dimensionTypeRegistration() {
        if (minestomInstance == null) return null;
        net.minestom.server.world.DimensionType dt = minestomInstance.getCachedDimensionType();
        return () -> dt;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isDebug() {
        return false;
    }
}
