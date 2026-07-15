package net.minecraft.world.level.dimension;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class LevelStem {
    public static final ResourceKey OVERWORLD = ResourceKey.createRoot(new Identifier("minecraft", "overworld"));
    public static final ResourceKey NETHER = ResourceKey.createRoot(new Identifier("minecraft", "the_nether"));
    public static final ResourceKey END = ResourceKey.createRoot(new Identifier("minecraft", "the_end"));

    public LevelStem(Holder dimensionType, ChunkGenerator generator) {
    }
}
