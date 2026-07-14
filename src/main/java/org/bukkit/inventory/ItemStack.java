package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStack implements Cloneable {
    private Material type;
    private int amount;
    private short durability;
    private byte data;
    private ItemMeta itemMeta;

    public ItemStack() { this(Material.AIR, 0); }
    public static ItemStack empty() { return new ItemStack(Material.AIR, 0); }
    public ItemStack(Material type) { this(type, 1); }
    public ItemStack(Material type, int amount) { this(type, amount, (short) 0); }
    public ItemStack(Material type, int amount, short durability) { this(type, amount, durability, (byte) 0); }
    public ItemStack(Material type, int amount, short durability, byte data) {
        this.type = type;
        this.amount = amount;
        this.durability = durability;
        this.data = data;
    }

    public Material getType() { return type; }
    public void setType(Material type) { this.type = type; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public short getDurability() { return durability; }
    public void setDurability(short durability) { this.durability = durability; }
    public byte getData() { return data; }
    public void setData(byte data) { this.data = data; }
    public int getMaxStackSize() { return type != null ? type.getMaxStackSize() : 64; }
    public boolean isSimilar(ItemStack stack) { return stack != null && type == stack.type && durability == stack.durability && data == stack.data; }
    public boolean equals(Object obj) { return obj instanceof ItemStack && isSimilar((ItemStack) obj) && amount == ((ItemStack) obj).amount; }
    public ItemMeta getItemMeta() {
        if (itemMeta == null) itemMeta = org.bukkit.Bukkit.getItemFactory().getItemMeta(type);
        return itemMeta;
    }
    public boolean setItemMeta(ItemMeta meta) { this.itemMeta = meta; return true; }
    public boolean hasItemMeta() { return true; }
    public java.util.List<net.kyori.adventure.text.Component> lore() { return getItemMeta().lore(); }
    public void lore(java.util.List<net.kyori.adventure.text.Component> lore) { getItemMeta().lore(lore); }
    public java.util.List<String> getLore() { return getItemMeta().getLore(); }
    public void setLore(java.util.List<String> lore) { getItemMeta().setLore(lore); }
    public String getDisplayName() { return getItemMeta().getDisplayName(); }
    public void setDisplayName(String name) { getItemMeta().setDisplayName(name); }
    public net.kyori.adventure.text.Component displayName() { return getItemMeta().displayName(); }
    public void displayName(net.kyori.adventure.text.Component displayName) { getItemMeta().displayName(displayName); }
    public org.bukkit.enchantments.Enchantment[] getEnchantments() { return new org.bukkit.enchantments.Enchantment[0]; }
    public boolean containsEnchantment(org.bukkit.enchantments.Enchantment enchantment) { return false; }
    public int getEnchantmentLevel(org.bukkit.enchantments.Enchantment enchantment) { return 0; }
    public void addEnchantment(org.bukkit.enchantments.Enchantment enchantment, int level) {}
    public void addUnsafeEnchantment(org.bukkit.enchantments.Enchantment enchantment, int level) {}
    public int removeEnchantment(org.bukkit.enchantments.Enchantment enchantment) { return 0; }
    public boolean isEmpty() { return type == Material.AIR || amount <= 0; }
    public ItemStack withType(Material type) {
        ItemStack copy = this.clone();
        copy.type = type;
        copy.itemMeta = null;
        return copy;
    }
    public java.util.Set<org.bukkit.inventory.ItemFlag> getItemFlags() {
        return getItemMeta().getItemFlags();
    }
    public void addItemFlags(org.bukkit.inventory.ItemFlag... itemFlags) {
        getItemMeta().addItemFlags(itemFlags);
    }
    public void removeItemFlags(org.bukkit.inventory.ItemFlag... itemFlags) {
        getItemMeta().removeItemFlags(itemFlags);
    }
    @Override public ItemStack clone() {
        ItemStack clone = new ItemStack(type, amount, durability, data);
        clone.itemMeta = this.itemMeta;
        return clone;
    }
    @Override public String toString() { return "ItemStack{type=" + type + ",amount=" + amount + "}"; }
    @Override public int hashCode() { return java.util.Objects.hash(type, amount, durability, data); }
}
