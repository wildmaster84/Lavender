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
        return null;
    }

    public org.bukkit.entity.Entity getBukkitEntity() {
        return null;
    }

    public boolean save(net.minecraft.world.level.storage.ValueOutput output) {
        return false;
    }

    public void saveWithoutId(net.minecraft.world.level.storage.ValueOutput output) {
    }

    public Vec3 getPosition(float partialTicks) {
        return new Vec3(
            minestomEntity.getPosition().x(),
            minestomEntity.getPosition().y(),
            minestomEntity.getPosition().z()
        );
    }

    public String getStringUUID() {
        return minestomEntity.getUuid().toString();
    }
}
