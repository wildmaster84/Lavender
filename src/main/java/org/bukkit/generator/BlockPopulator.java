package org.bukkit.generator;

import org.bukkit.World;
import java.util.Random;

public abstract class BlockPopulator {

    public abstract void populate(World world, Random random, int chunkX, int chunkZ);
}
