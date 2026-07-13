package org.bukkit.inventory;

import org.bukkit.Material;
import java.util.HashMap;

public interface Inventory {
    int getSize();
    int getMaxStackSize();
    void setMaxStackSize(int size);
    String getName();
    String getTitle();
    ItemStack[] getContents();
    void setContents(ItemStack[] items);
    ItemStack[] getStorageContents();
    void setStorageContents(ItemStack[] items);
    ItemStack getItem(int index);
    void setItem(int index, ItemStack item);
    HashMap<Integer, ItemStack> removeItem(ItemStack... items);
    HashMap<Integer, ItemStack> addItem(ItemStack... items);
    ItemStack[] getArmorContents();
    void setArmorContents(ItemStack[] items);
    boolean contains(Material material);
    boolean contains(ItemStack item);
    boolean contains(Material material, int amount);
    boolean contains(ItemStack item, int amount);
    int first(Material material);
    int first(ItemStack item);
    int firstEmpty();
    boolean isEmpty();
    void clear();
    void clear(int index);
    InventoryHolder getHolder();
    java.util.ListIterator<ItemStack> iterator();
    java.util.ListIterator<ItemStack> iterator(int index);
    java.util.List<HumanEntity> getViewers();
    org.bukkit.inventory.InventoryType getType();
}
