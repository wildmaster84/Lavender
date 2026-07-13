package org.bukkit.inventory.meta;

import org.bukkit.entity.EntityType;

public interface SpawnEggMeta extends ItemMeta {
    EntityType getCustomSpawnedType();
    void setCustomSpawnedType(EntityType type);
    EntityType getSpawnedType();
    void setSpawnedType(EntityType type);
}
