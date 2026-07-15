package org.bukkit.craftbukkit.entity;

import net.minecraft.server.level.ServerPlayer;

public interface CraftPlayer extends CraftEntity, org.bukkit.entity.Player {
    @Override
    ServerPlayer getHandle();
}
