package me.wildmaster84.adapter.inventory;

import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import org.bukkit.Material;

public class LavenderInventory implements org.bukkit.inventory.Inventory {

    private static final java.util.Map<Inventory, LavenderInventory> REGISTRY = new java.util.concurrent.ConcurrentHashMap<>();

    public static LavenderInventory wrap(Inventory msInv) {
        return REGISTRY.get(msInv);
    }

    public static void register(Inventory msInv, LavenderInventory wrapper) {
        REGISTRY.put(msInv, wrapper);
    }

    private final Inventory msInventory;
    private final String title;
    private final org.bukkit.inventory.InventoryHolder holder;
    private final org.bukkit.inventory.InventoryType bukkitType;
    private final java.util.Map<Integer, org.bukkit.inventory.ItemStack> bukkitItems = new java.util.concurrent.ConcurrentHashMap<>();

    public LavenderInventory(Inventory msInventory, String title, org.bukkit.inventory.InventoryHolder holder, org.bukkit.inventory.InventoryType bukkitType) {
        this.msInventory = msInventory;
        this.title = title;
        this.holder = holder;
        this.bukkitType = bukkitType;
        register(msInventory, this);
    }

    public Inventory getMinestomInventory() { return msInventory; }

    private org.bukkit.inventory.ItemStack convert(ItemStack msItem) {
        if (msItem == null || msItem.isAir()) return new org.bukkit.inventory.ItemStack();
        Material mat = Material.matchMaterial(msItem.material().name());
        if (mat == null) mat = Material.AIR;
        return new org.bukkit.inventory.ItemStack(mat, msItem.amount());
    }

    private ItemStack convertBack(org.bukkit.inventory.ItemStack bukkitItem) {
        if (bukkitItem == null || bukkitItem.getType() == Material.AIR || bukkitItem.getAmount() <= 0) {
            return ItemStack.AIR;
        }
        try {
            String name = bukkitItem.getType().name().toLowerCase();
            net.minestom.server.item.Material msMat = net.minestom.server.item.Material.fromKey("minecraft:" + name);
            if (msMat == null) msMat = net.minestom.server.item.Material.fromKey(name);
            if (msMat == null) return ItemStack.AIR;
            ItemStack.Builder builder = ItemStack.builder(msMat).amount(bukkitItem.getAmount());
            org.bukkit.inventory.meta.ItemMeta meta = bukkitItem.getItemMeta();
            if (meta != null) {
                if (meta.hasDisplayName()) {
                    String dn = meta.getDisplayName();
                    if (dn != null && !dn.isEmpty()) {
                        builder.customName(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(dn));
                    }
                }
                java.util.List<net.kyori.adventure.text.Component> loreComponents = meta.lore();
                if (loreComponents != null && !loreComponents.isEmpty()) {
                    builder.lore(loreComponents);
                } else {
                    java.util.List<String> lore = meta.getLore();
                    if (lore != null && !lore.isEmpty()) {
                        java.util.List<net.kyori.adventure.text.Component> components = new java.util.ArrayList<>();
                        for (String line : lore) {
                            components.add(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(line));
                        }
                        builder.lore(components);
                    }
                }
            }
            return builder.build();
        } catch (Exception e) {
            return ItemStack.AIR;
        }
    }

    @Override public int getSize() { return msInventory.getSize(); }
    @Override public int getMaxStackSize() { return 64; }
    @Override public void setMaxStackSize(int size) {}
    @Override public String getName() { return title; }
    @Override public String getTitle() { return title; }

    @Override
    public org.bukkit.inventory.ItemStack[] getContents() {
        int size = msInventory.getSize();
        org.bukkit.inventory.ItemStack[] contents = new org.bukkit.inventory.ItemStack[size];
        for (int i = 0; i < size; i++) {
            org.bukkit.inventory.ItemStack cached = bukkitItems.get(i);
            contents[i] = cached != null ? cached : convert(msInventory.getItemStack(i));
        }
        return contents;
    }

    @Override
    public void setContents(org.bukkit.inventory.ItemStack[] items) {
        if (items == null) return;
        for (int i = 0; i < Math.min(items.length, msInventory.getSize()); i++) {
            msInventory.setItemStack(i, convertBack(items[i]));
        }
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getStorageContents() { return getContents(); }
    @Override
    public void setStorageContents(org.bukkit.inventory.ItemStack[] items) { setContents(items); }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int index) {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(index);
        if (cached != null) return cached;
        return convert(msInventory.getItemStack(index));
    }

    @Override
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
        if (item == null || item.getType() == Material.AIR || item.getAmount() <= 0) {
            bukkitItems.remove(index);
        } else {
            bukkitItems.put(index, item);
        }
        msInventory.setItemStack(index, convertBack(item));
    }

    @Override
    public java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> removeItem(org.bukkit.inventory.ItemStack... items) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> addItem(org.bukkit.inventory.ItemStack... items) {
        java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> overflow = new java.util.HashMap<>();
        for (org.bukkit.inventory.ItemStack item : items) {
            if (item == null || item.getType() == Material.AIR || item.getAmount() <= 0) continue;
            int remaining = item.getAmount();
            for (int i = 0; i < msInventory.getSize() && remaining > 0; i++) {
                ItemStack existing = msInventory.getItemStack(i);
                if (!existing.isAir() && existing.material().name().equals(item.getType().name()) && existing.amount() < existing.maxStackSize()) {
                    int canAdd = existing.maxStackSize() - existing.amount();
                    int toAdd = Math.min(remaining, canAdd);
                    msInventory.setItemStack(i, existing.withAmount(existing.amount() + toAdd));
                    remaining -= toAdd;
                }
            }
            for (int i = 0; i < msInventory.getSize() && remaining > 0; i++) {
                ItemStack existing = msInventory.getItemStack(i);
                if (existing.isAir()) {
                    int toAdd = Math.min(remaining, item.getType().getMaxStackSize());
                    msInventory.setItemStack(i, convertBack(item).withAmount(toAdd));
                    remaining -= toAdd;
                }
            }
            if (remaining > 0) overflow.put(-1, new org.bukkit.inventory.ItemStack(item.getType(), remaining));
        }
        return overflow;
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getArmorContents() { return new org.bukkit.inventory.ItemStack[0]; }
    @Override
    public void setArmorContents(org.bukkit.inventory.ItemStack[] items) {}

    @Override public boolean contains(Material material) { return false; }
    @Override public boolean contains(org.bukkit.inventory.ItemStack item) { return false; }
    @Override public boolean contains(Material material, int amount) { return false; }
    @Override public boolean contains(org.bukkit.inventory.ItemStack item, int amount) { return false; }
    @Override public int first(Material material) { return -1; }
    @Override public int first(org.bukkit.inventory.ItemStack item) { return -1; }

    @Override
    public int firstEmpty() {
        for (int i = 0; i < msInventory.getSize(); i++) {
            if (msInventory.getItemStack(i).isAir()) return i;
        }
        return -1;
    }

    @Override public boolean isEmpty() { return false; }
    @Override public void clear() { msInventory.clear(); }
    @Override public void clear(int index) { msInventory.setItemStack(index, ItemStack.AIR); }
    @Override public org.bukkit.inventory.InventoryHolder getHolder() { return holder; }

    @Override
    public java.util.ListIterator<org.bukkit.inventory.ItemStack> iterator() {
        return java.util.Arrays.asList(getContents()).listIterator();
    }

    @Override
    public java.util.ListIterator<org.bukkit.inventory.ItemStack> iterator(int index) {
        return java.util.Arrays.asList(getContents()).listIterator(index);
    }

    @Override
    public java.util.List<org.bukkit.inventory.HumanEntity> getViewers() {
        return java.util.Collections.emptyList();
    }

    @Override
    public org.bukkit.inventory.InventoryType getType() { return bukkitType; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof org.bukkit.inventory.Inventory other)) return false;
        if (other instanceof LavenderInventory mi) {
            return this.msInventory == mi.msInventory;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return msInventory.hashCode();
    }
}
