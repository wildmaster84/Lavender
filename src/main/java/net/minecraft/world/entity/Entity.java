package net.minecraft.world.entity;

import net.minecraft.world.phys.Vec3;

public class Entity {
    private final net.minestom.server.entity.Entity minestomEntity;

    public Entity(net.minestom.server.entity.Entity minestomEntity) {
        this.minestomEntity = minestomEntity;
    }

    public net.minestom.server.entity.Entity getMinestomEntity() {
        return minestomEntity;
    }

    public EntityType<?> getType() {
        return new EntityType<>(minestomEntity.getEntityType());
    }

    public org.bukkit.entity.Entity getBukkitEntity() {
        if (minestomEntity instanceof net.minestom.server.entity.Player msPlayer) {
            org.bukkit.Server bukkitServer = org.bukkit.Bukkit.getServer();
            if (bukkitServer instanceof me.wildmaster84.adapter.server.LavenderServer ls) {
                return me.wildmaster84.adapter.player.LavenderPlayer.wrap(msPlayer, ls);
            }
        }
        org.bukkit.Server bukkitServer = org.bukkit.Bukkit.getServer();
        if (bukkitServer instanceof me.wildmaster84.adapter.server.LavenderServer ls) {
            return new me.wildmaster84.adapter.entity.LavenderEntity(minestomEntity, ls);
        }
        return null;
    }

    public boolean save(net.minecraft.world.level.storage.ValueOutput output) {
        return true;
    }

    public void saveWithoutId(net.minecraft.world.level.storage.ValueOutput output) {
    }

    public Vec3 getPosition(float partialTicks) {
        net.minestom.server.coordinate.Pos pos = minestomEntity.getPosition();
        return new Vec3(pos.x(), pos.y(), pos.z());
    }

    public String getStringUUID() {
        return minestomEntity.getUuid().toString();
    }
}
