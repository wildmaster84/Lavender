package net.minecraft.world.level.levelgen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.ChunkPos;

public class StructureStart {
    public BoundingBox getBoundingBox() {
        return new BoundingBox(0, 0, 0, 0, 0, 0);
    }

    public boolean isValid() {
        return false;
    }

    public void placeInChunk(
            WorldGenLevel level,
            StructureManager structureManager,
            ChunkGenerator generator,
            RandomSource random,
            BoundingBox boundingBox,
            ChunkPos chunkPos
    ) {
    }
}
