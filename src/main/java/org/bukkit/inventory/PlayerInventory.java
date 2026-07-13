package org.bukkit.inventory;

import org.bukkit.Material;
import java.util.HashMap;

public interface PlayerInventory extends Inventory {
    ItemStack[] getArmorContents();
    void setArmorContents(ItemStack[] items);
    ItemStack getHelmet();
    void setHelmet(ItemStack helmet);
    ItemStack getChestplate();
    void setChestplate(ItemStack chestplate);
    ItemStack getLeggings();
    void setLeggings(ItemStack leggings);
    ItemStack getBoots();
    void setBoots(ItemStack boots);
    ItemStack getItemInMainHand();
    void setItemInMainHand(ItemStack item);
    ItemStack getItemInOffHand();
    void setItemInOffHand(ItemStack item);
    int getHeldItemSlot();
    void setHeldItemSlot(int slot);
    int first(ItemStack item);
    int first(Material material);
    boolean contains(Material material);
    boolean contains(ItemStack item);
    boolean contains(Material material, int amount);
    boolean contains(ItemStack item, int amount);
    HashMap<Integer, ItemStack> removeItem(ItemStack... items);
    ItemStack[] getContents();
    void setContents(ItemStack[] items);
    ItemStack[] getStorageContents();
    void setStorageContents(ItemStack[] items);
    int getSize();
    int getMaxStackSize();
    void setMaxStackSize(int size);
    String getName();
    String getTitle();
    boolean isEmpty();
    void clear();
    void clear(int index);
    ItemStack getItem(int index);
    void setItem(int index, ItemStack item);
    InventoryHolder getHolder();
}
