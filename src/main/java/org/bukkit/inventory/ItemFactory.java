package org.bukkit.inventory;

import org.bukkit.inventory.meta.ItemMeta;

public interface ItemFactory {
    ItemMeta getItemMeta(org.bukkit.Material material);
    boolean isApplicable(org.bukkit.inventory.ItemStack itemStack, org.bukkit.Material material);
    boolean isApplicable(ItemMeta itemMeta, org.bukkit.Material material);
    boolean isApplicable(org.bukkit.inventory.ItemStack itemStack, ItemMeta itemMeta);
    ItemMeta asMetaFor(org.bukkit.inventory.ItemStack itemStack, ItemMeta itemMeta);
    ItemMeta asMetaFor(ItemMeta itemMeta, org.bukkit.Material material);
    boolean equals(ItemMeta itemMeta1, ItemMeta itemMeta2);
    org.bukkit.inventory.ItemStack createItemStack(String material);
    default org.bukkit.inventory.ItemStack createItemStack(org.bukkit.Material material) {
        return new org.bukkit.inventory.ItemStack(material);
    }
    default org.bukkit.inventory.ItemStack createItemStack(org.bukkit.Material material, int amount) {
        return new org.bukkit.inventory.ItemStack(material, amount);
    }
    String serialize(ItemMeta itemMeta);
    ItemMeta deserialize(String data);
}
