package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;

public class LevelChunk extends ChunkAccess {
    private final net.minestom.server.instance.Instance instance;
    private final int chunkX;
    private final int chunkZ;

    public LevelChunk() {
        this.instance = null;
        this.chunkX = 0;
        this.chunkZ = 0;
    }

    public LevelChunk(net.minestom.server.instance.Instance instance, int chunkX, int chunkZ) {
        this.instance = instance;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        if (instance == null) return new BlockState();
        net.minestom.server.instance.block.Block msBlock = instance.getBlock(pos.getX(), pos.getY(), pos.getZ());
        if (msBlock == null) msBlock = net.minestom.server.instance.block.Block.AIR;
        return new BlockState(Block.of(msBlock.defaultState()), msBlock);
    }

    public BlockState setBlockState(BlockPos pos, BlockState state, int flags) {
        if (instance == null) return new BlockState();
        net.minestom.server.instance.block.Block oldBlock = instance.getBlock(pos.getX(), pos.getY(), pos.getZ());
        BlockState oldState;
        if (oldBlock == null) {
            oldState = new BlockState();
        } else {
            oldState = new BlockState(Block.of(oldBlock.defaultState()), oldBlock);
        }
        instance.setBlock(pos.getX(), pos.getY(), pos.getZ(), state.getMinestomBlock());
        return oldState;
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }

    public Holder getNoiseBiome(int x, int y, int z) {
        return null;
    }

    @Override
    public ChunkPos getPos() {
        return new ChunkPos(chunkX, chunkZ);
    }

    public int getSectionIndex(int y) {
        if (instance != null) {
            return Math.floorDiv(y - instance.getCachedDimensionType().minY(), 16);
        }
        return 0;
    }

    public LevelChunkSection getSection(int index) {
        return new LevelChunkSection();
    }

    public LevelChunkSection[] getSections() {
        if (instance != null) {
            int minSection = instance.getCachedDimensionType().minY() / 16;
            int maxSection = (instance.getCachedDimensionType().maxY() + 1) / 16;
            return new LevelChunkSection[maxSection - minSection];
        }
        return new LevelChunkSection[0];
    }

    public void markUnsaved() {
    }

    public FullChunkStatus getFullStatus() {
        return FullChunkStatus.BLOCK_TICKING;
    }
}
