package org.bukkit.enchantments;

public abstract class Enchantment {
    public static final Enchantment PROTECTION_ENVIRONMENTAL = null;
    public static final Enchantment PROTECTION_FIRE = null;
    public static final Enchantment PROTECTION_FALL = null;
    public static final Enchantment PROTECTION_EXPLOSIONS = null;
    public static final Enchantment PROTECTION_PROJECTILE = null;
    public static final Enchantment OXYGEN = null;
    public static final Enchantment WATER_WORKER = null;
    public static final Enchantment THORNS = null;
    public static final Enchantment DEPTH_STRIDER = null;
    public static final Enchantment FROST_WALKER = null;
    public static final Enchantment BINDING_CURSE = null;
    public static final Enchantment DAMAGE_ALL = null;
    public static final Enchantment DAMAGE_UNDEAD = null;
    public static final Enchantment DAMAGE_ARTHROPODS = null;
    public static final Enchantment KNOCKBACK = null;
    public static final Enchantment FIRE_ASPECT = null;
    public static final Enchantment LOOT_BONUS_MOBS = null;
    public static final Enchantment SWEEPING_EDGE = null;
    public static final Enchantment DIG_SPEED = null;
    public static final Enchantment SILK_TOUCH = null;
    public static final Enchantment DURABILITY = null;
    public static final Enchantment LOOT_BONUS_BLOCKS = null;
    public static final Enchantment ARROW_DAMAGE = null;
    public static final Enchantment ARROW_KNOCKBACK = null;
    public static final Enchantment ARROW_FIRE = null;
    public static final Enchantment ARROW_INFINITE = null;
    public static final Enchantment LUCK = null;
    public static final Enchantment LURE = null;
    public static final Enchantment LOYALTY = null;
    public static final Enchantment IMPALING = null;
    public static final Enchantment RIPTIDE = null;
    public static final Enchantment CHANNELING = null;
    public static final Enchantment MULTISHOT = null;
    public static final Enchantment QUICK_CHARGE = null;
    public static final Enchantment PIERCING = null;
    public static final Enchantment MENDING = null;
    public static final Enchantment VANISHING_CURSE = null;
    public static final Enchantment SOUL_SPEED = null;
    public static final Enchantment SWIFT_SNEAK = null;

    public abstract int getMaxLevel();
    public abstract int getStartLevel();
    public abstract org.bukkit.enchantments.EnchantmentTarget getItemTarget();
    public abstract boolean canEnchantItem(org.bukkit.inventory.ItemStack item);
    public abstract String getName();
    public abstract boolean conflictsWith(Enchantment other);
    public int getId() { return 0; }
    public boolean isCursed() { return false; }
    public boolean isTreasure() { return false; }
    public java.lang.String getKey() { return getName().toLowerCase(); }
}
