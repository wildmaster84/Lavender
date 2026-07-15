package net.minecraft.world.level.levelgen.structure;

import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class Structure {
    public static StructureStart generate(
            Holder<Structure> structure,
            ResourceKey key,
            RegistryAccess registryAccess,
            ChunkGenerator generator,
            BiomeSource biomeSource,
            RandomState randomState,
            StructureTemplateManager templateManager,
            long seed,
            ChunkPos chunkPos,
            int i,
            LevelHeightAccessor heightAccessor,
            Predicate<Holder<Structure>> predicate
    ) {
        return new StructureStart();
    }
}
