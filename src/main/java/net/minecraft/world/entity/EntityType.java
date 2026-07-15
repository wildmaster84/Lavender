package net.minecraft.world.entity;

import net.minecraft.resources.Identifier;

public class EntityType<T extends Entity> {
    public static Identifier getKey(EntityType<?> type) {
        return null;
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
