package me.wildmaster84.adapter.server.entity;

import org.bukkit.entity.Player;

public interface CraftPlayer extends Player {
    com.mojang.authlib.GameProfile getProfile();
    net.minecraft.server.level.ServerPlayer getHandle();
}
