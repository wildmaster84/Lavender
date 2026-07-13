package org.bukkit.inventory.meta;

import org.bukkit.DyeColor;

public interface ShieldMeta extends BlockStateMeta {
    DyeColor getBaseColor();
    void setBaseColor(DyeColor color);
    boolean hasBaseColor();
}
