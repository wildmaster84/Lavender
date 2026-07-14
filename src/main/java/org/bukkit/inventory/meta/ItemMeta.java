package org.bukkit.inventory.meta;

import java.util.List;
import java.util.Map;

public interface ItemMeta extends Cloneable {
    boolean hasDisplayName();
    String getDisplayName();
    void setDisplayName(String name);
    net.kyori.adventure.text.Component displayName();
    void displayName(net.kyori.adventure.text.Component displayName);
    boolean hasLocalizedName();
    String getLocalizedName();
    void setLocalizedName(String name);
    boolean hasLore();
    List<String> getLore();
    void setLore(List<String> lore);
    void lore(java.util.List<net.kyori.adventure.text.Component> lore);
    java.util.List<net.kyori.adventure.text.Component> lore();
    boolean hasCustomModelData();
    int getCustomModelData();
    void setCustomModelData(Integer data);
    boolean hasEnchants();
    boolean hasRepairCost();
    int getRepairCost();
    void setRepairCost(int cost);
    boolean hasAttributeModifiers();
    boolean addAttributeModifier(org.bukkit.attribute.Attribute attribute, org.bukkit.attribute.AttributeModifier modifier);
    boolean removeAttributeModifier(org.bukkit.attribute.Attribute attribute);
    com.google.common.collect.Multimap<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> getAttributeModifiers();
    void setAttributeModifiers(com.google.common.collect.Multimap<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> attributeModifiers);
    void setUnbreakable(boolean unbreakable);
    boolean isUnbreakable();
    void addItemFlags(org.bukkit.inventory.ItemFlag... itemFlags);
    void removeItemFlags(org.bukkit.inventory.ItemFlag... itemFlags);
    java.util.Set<org.bukkit.inventory.ItemFlag> getItemFlags();
    boolean hasCustomTag();
    int getEnchantLevel(org.bukkit.enchantments.Enchantment ench);
    java.util.Map<org.bukkit.enchantments.Enchantment, Integer> getEnchants();
    boolean removeEnchant(org.bukkit.enchantments.Enchantment ench);
    void removeEnchantments();
    Map<String, Object> serialize();
    ItemMeta clone();
}
