package org.bukkit.inventory;

public interface EntityEquipment {
    org.bukkit.inventory.ItemStack getItemInMainHand();
    void setItemInMainHand(org.bukkit.inventory.ItemStack item);
    org.bukkit.inventory.ItemStack getItemInOffHand();
    void setItemInOffHand(org.bukkit.inventory.ItemStack item);
    org.bukkit.inventory.ItemStack getHelmet();
    void setHelmet(org.bukkit.inventory.ItemStack helmet);
    org.bukkit.inventory.ItemStack getChestplate();
    void setChestplate(org.bukkit.inventory.ItemStack chestplate);
    org.bukkit.inventory.ItemStack getLeggings();
    void setLeggings(org.bukkit.inventory.ItemStack leggings);
    org.bukkit.inventory.ItemStack getBoots();
    void setBoots(org.bukkit.inventory.ItemStack boots);
    org.bukkit.inventory.ItemStack[] getArmorContents();
    void setArmorContents(org.bukkit.inventory.ItemStack[] items);
    org.bukkit.inventory.ItemStack getItemInHand();
    void setItemInHand(org.bukkit.inventory.ItemStack item);
    org.bukkit.inventory.ItemStack[] getEquipment();
    float getItemInHandDropChance();
    void setItemInHandDropChance(float chance);
    float getHelmetDropChance();
    void setHelmetDropChance(float chance);
    float getChestplateDropChance();
    void setChestplateDropChance(float chance);
    float getLeggingsDropChance();
    void setLeggingsDropChance(float chance);
    float getBootsDropChance();
    void setBootsDropChance(float chance);
}