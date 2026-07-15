package net.minecraft.world.level;

import java.util.stream.Stream;
import net.minecraft.core.BlockPos;

public class ChunkPos {
    public final int x;
    public final int z;

    public ChunkPos(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public static ChunkPos containing(BlockPos pos) {
        return new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public static Stream<ChunkPos> rangeClosed(ChunkPos from, ChunkPos to) {
        return Stream.empty();
    }

    public int getMinBlockX() {
        return x << 4;
    }

    public int getMinBlockZ() {
        return z << 4;
    }

    public int getMaxBlockX() {
        return (x << 4) + 15;
    }

    public int getMaxBlockZ() {
        return (z << 4) + 15;
    }

    public long toLong() {
        return ((long) x << 32) | (z & 0xFFFFFFFFL);
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }
}
