package org.bukkit.craftbukkit;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.World;

public abstract class CraftWorld implements World {
    public abstract ServerLevel getHandle();
}
