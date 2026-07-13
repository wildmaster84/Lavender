package org.bukkit.inventory;

public enum EquipmentSlot {
    HAND,
    OFF_HAND,
    FEET,
    LEGS,
    CHEST,
    HEAD,
    BODY;

    public static EquipmentSlot getBySlot(int slot) {
        switch (slot) {
            case 0: return HAND;
            case 1: return OFF_HAND;
            case 2: return FEET;
            case 3: return LEGS;
            case 4: return CHEST;
            case 5: return HEAD;
            default: return HAND;
        }
    }
}
