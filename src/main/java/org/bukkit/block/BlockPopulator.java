package org.bukkit.block;

import org.bukkit.World;
import java.util.Random;

public abstract class BlockPopulator {
    public abstract void populate(World world, Random random, org.bukkit.Chunk source);
}
