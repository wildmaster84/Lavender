package org.bukkit.craftbukkit.entity;

import net.minecraft.world.entity.Entity;

public interface CraftEntity extends org.bukkit.entity.Entity {
    Entity getHandle();
}
