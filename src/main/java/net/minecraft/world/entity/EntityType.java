package net.minecraft.world.entity;

import net.minecraft.resources.Identifier;

public class EntityType<T extends Entity> {
    private final net.minestom.server.entity.EntityType minestomType;

    public EntityType(net.minestom.server.entity.EntityType minestomType) {
        this.minestomType = minestomType;
    }

    public net.minestom.server.entity.EntityType getMinestomType() {
        return minestomType;
    }

    public static Identifier getKey(EntityType<?> type) {
        if (type == null || type.minestomType == null) return null;
        net.kyori.adventure.key.Key key = type.minestomType.key();
        return new Identifier(key.namespace(), key.value());
    }

    public static Entity loadEntityRecursive(
            net.minecraft.nbt.CompoundTag tag,
            net.minecraft.world.level.Level level,
            EntitySpawnReason reason,
            EntityProcessor processor
    ) {
        return null;
    }
}
