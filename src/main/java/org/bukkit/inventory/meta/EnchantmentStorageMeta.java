package org.bukkit.inventory.meta;

import org.bukkit.enchantments.Enchantment;

public interface EnchantmentStorageMeta extends ItemMeta {
    boolean addStoredEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction);
    boolean removeStoredEnchant(Enchantment ench);
    boolean hasStoredEnchants();
    boolean hasStoredEnchant(Enchantment ench);
    int getStoredEnchantLevel(Enchantment ench);
    java.util.Map<Enchantment, Integer> getStoredEnchants();
    boolean addEnchantments(java.util.Map<Enchantment, Integer> enchantments);
}
