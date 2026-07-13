package me.wildmaster84.adapter.inventory;

import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;

public class SimpleItemFactory implements ItemFactory {
    @Override public ItemMeta getItemMeta(Material material) { return new SimpleItemMeta(); }
    @Override public boolean isApplicable(org.bukkit.inventory.ItemStack itemStack, Material material) { return false; }
    @Override public boolean isApplicable(ItemMeta itemMeta, Material material) { return false; }
    @Override public boolean isApplicable(org.bukkit.inventory.ItemStack itemStack, ItemMeta itemMeta) { return false; }
    @Override public ItemMeta asMetaFor(org.bukkit.inventory.ItemStack itemStack, ItemMeta itemMeta) { return null; }
    @Override public ItemMeta asMetaFor(ItemMeta itemMeta, Material material) { return null; }
    @Override public boolean equals(ItemMeta itemMeta1, ItemMeta itemMeta2) { return false; }
    @Override public org.bukkit.inventory.ItemStack createItemStack(String material) { return new org.bukkit.inventory.ItemStack(Material.STONE); }
    @Override public String serialize(ItemMeta itemMeta) { return ""; }
    @Override public ItemMeta deserialize(String data) { return null; }
}
