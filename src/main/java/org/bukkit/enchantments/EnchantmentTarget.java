package org.bukkit.enchantments;

import org.bukkit.Material;

public enum EnchantmentTarget {
    ALL,
    ARMOR,
    ARMOR_FEET,
    ARMOR_LEGS,
    ARMOR_TORSO,
    ARMOR_HEAD,
    WEAPON,
    TOOL,
    BOW,
    FISHING_ROD,
    BREAKABLE,
    WEARABLE,
    CROSSBOW,
    TRIDENT,
    VANISHABLE;

    public boolean includes(Material item) { return true; }
}
