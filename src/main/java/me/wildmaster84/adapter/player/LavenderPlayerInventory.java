package me.wildmaster84.adapter.player;

import net.minestom.server.entity.Player;
import net.minestom.server.entity.EquipmentSlot;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.item.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class LavenderPlayerInventory implements org.bukkit.inventory.PlayerInventory {

    private final Player player;
    private final Map<Integer, org.bukkit.inventory.ItemStack> bukkitItems = new HashMap<>();

    public LavenderPlayerInventory(Player player) {
        this.player = player;
    }

    private PlayerInventory inv() { return player.getInventory(); }

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
            String materialName = bukkitItem.getType().name().toLowerCase();
            net.minestom.server.item.Material msMat = net.minestom.server.item.Material.fromKey("minecraft:" + materialName);
            if (msMat == null) {
                msMat = net.minestom.server.item.Material.fromKey(materialName);
            }
            if (msMat == null) {
                return ItemStack.AIR;
            }
            ItemStack.Builder builder = ItemStack.builder(msMat).amount(bukkitItem.getAmount());
            org.bukkit.inventory.meta.ItemMeta meta = bukkitItem.getItemMeta();
            if (meta != null) {
                if (meta.hasDisplayName()) {
                    String name = meta.getDisplayName();
                    if (name != null && !name.isEmpty()) {
                        builder.customName(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(name));
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

    private static final int SLOT_MAIN_HAND = -1;
    private static final int SLOT_OFF_HAND = 45;
    private static final int SLOT_HELMET = 39;
    private static final int SLOT_CHESTPLATE = 38;
    private static final int SLOT_LEGGINGS = 37;
    private static final int SLOT_BOOTS = 36;

    @Override
    public org.bukkit.inventory.ItemStack getItemInMainHand() {
        int slot = player.getHeldSlot();
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(slot);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.MAIN_HAND, player.getHeldSlot()));
    }

    @Override
    public void setItemInMainHand(org.bukkit.inventory.ItemStack item) {
        int slot = player.getHeldSlot();
        bukkitItems.put(slot, item);
        inv().setEquipment(EquipmentSlot.MAIN_HAND, player.getHeldSlot(), convertBack(item));
    }

    @Override
    public org.bukkit.inventory.ItemStack getItemInOffHand() {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(SLOT_OFF_HAND);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.OFF_HAND, (byte) 0));
    }

    @Override
    public void setItemInOffHand(org.bukkit.inventory.ItemStack item) {
        bukkitItems.put(SLOT_OFF_HAND, item);
        inv().setEquipment(EquipmentSlot.OFF_HAND, (byte) 0, convertBack(item));
    }

    @Override
    public org.bukkit.inventory.ItemStack getHelmet() {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(SLOT_HELMET);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.HELMET, (byte) 0));
    }

    @Override
    public void setHelmet(org.bukkit.inventory.ItemStack helmet) {
        bukkitItems.put(SLOT_HELMET, helmet);
        inv().setEquipment(EquipmentSlot.HELMET, (byte) 0, convertBack(helmet));
    }

    @Override
    public org.bukkit.inventory.ItemStack getChestplate() {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(SLOT_CHESTPLATE);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.CHESTPLATE, (byte) 0));
    }

    @Override
    public void setChestplate(org.bukkit.inventory.ItemStack chestplate) {
        bukkitItems.put(SLOT_CHESTPLATE, chestplate);
        inv().setEquipment(EquipmentSlot.CHESTPLATE, (byte) 0, convertBack(chestplate));
    }

    @Override
    public org.bukkit.inventory.ItemStack getLeggings() {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(SLOT_LEGGINGS);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.LEGGINGS, (byte) 0));
    }

    @Override
    public void setLeggings(org.bukkit.inventory.ItemStack leggings) {
        bukkitItems.put(SLOT_LEGGINGS, leggings);
        inv().setEquipment(EquipmentSlot.LEGGINGS, (byte) 0, convertBack(leggings));
    }

    @Override
    public org.bukkit.inventory.ItemStack getBoots() {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(SLOT_BOOTS);
        if (cached != null) return cached;
        return convert(inv().getEquipment(EquipmentSlot.BOOTS, (byte) 0));
    }

    @Override
    public void setBoots(org.bukkit.inventory.ItemStack boots) {
        bukkitItems.put(SLOT_BOOTS, boots);
        inv().setEquipment(EquipmentSlot.BOOTS, (byte) 0, convertBack(boots));
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getArmorContents() {
        return new org.bukkit.inventory.ItemStack[] { getHelmet(), getChestplate(), getLeggings(), getBoots() };
    }

    @Override
    public void setArmorContents(org.bukkit.inventory.ItemStack[] items) {
        if (items == null || items.length < 4) return;
        setHelmet(items[0]);
        setChestplate(items[1]);
        setLeggings(items[2]);
        setBoots(items[3]);
    }

    @Override
    public int getHeldItemSlot() { return player.getHeldSlot(); }

    @Override
    public void setHeldItemSlot(int slot) { player.refreshHeldSlot((byte) slot); }

    @Override
    public int first(org.bukkit.inventory.ItemStack item) { return -1; }

    @Override
    public int first(Material material) { return -1; }

    @Override
    public boolean contains(Material material) { return false; }

    @Override
    public boolean contains(org.bukkit.inventory.ItemStack item) { return false; }

    @Override
    public boolean contains(Material material, int amount) { return false; }

    @Override
    public boolean contains(org.bukkit.inventory.ItemStack item, int amount) { return false; }

    @Override
    public java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> removeItem(org.bukkit.inventory.ItemStack... items) {
        return new java.util.HashMap<>();
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getContents() {
        ItemStack[] msItems = inv().getItemStacks();
        org.bukkit.inventory.ItemStack[] contents = new org.bukkit.inventory.ItemStack[msItems.length];
        for (int i = 0; i < msItems.length; i++) {
            org.bukkit.inventory.ItemStack cached = bukkitItems.get(i);
            contents[i] = cached != null ? cached : convert(msItems[i]);
        }
        return contents;
    }

    @Override
    public void setContents(org.bukkit.inventory.ItemStack[] items) {
        if (items == null) return;
        for (int i = 0; i < items.length; i++) {
            bukkitItems.put(i, items[i]);
            inv().setItemStack(i, convertBack(items[i]));
        }
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getStorageContents() {
        org.bukkit.inventory.ItemStack[] contents = new org.bukkit.inventory.ItemStack[36];
        for (int i = 0; i < 36; i++) {
            org.bukkit.inventory.ItemStack cached = bukkitItems.get(i);
            contents[i] = cached != null ? cached : convert(inv().getItemStack(i));
        }
        return contents;
    }

    @Override
    public void setStorageContents(org.bukkit.inventory.ItemStack[] items) {
        if (items == null) return;
        for (int i = 0; i < Math.min(items.length, 36); i++) {
            bukkitItems.put(i, items[i]);
            inv().setItemStack(i, convertBack(items[i]));
        }
    }

    @Override public int getSize() { return 41; }
    @Override public int getMaxStackSize() { return 64; }
    @Override public void setMaxStackSize(int size) {}
    @Override public String getName() { return "Player Inventory"; }
    @Override public String getTitle() { return "Player Inventory"; }
    @Override public boolean isEmpty() { return false; }
    @Override public void clear() { inv().clear(); bukkitItems.clear(); }
    @Override public void clear(int index) { inv().setItemStack(index, ItemStack.AIR); bukkitItems.remove(index); }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int index) {
        org.bukkit.inventory.ItemStack cached = bukkitItems.get(index);
        if (cached != null) return cached;
        return convert(inv().getItemStack(index));
    }

    @Override
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
        bukkitItems.put(index, item);
        inv().setItemStack(index, convertBack(item));
    }

    @Override
    public InventoryHolder getHolder() { return () -> LavenderPlayerInventory.this; }

    @Override
    public java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> addItem(org.bukkit.inventory.ItemStack... items) {
        java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> overflow = new java.util.HashMap<>();
        for (org.bukkit.inventory.ItemStack item : items) {
            if (item == null || item.getType() == Material.AIR || item.getAmount() <= 0) continue;
            int remaining = item.getAmount();
            for (int i = 0; i < 36 && remaining > 0; i++) {
                ItemStack existing = inv().getItemStack(i);
                if (!existing.isAir() && existing.material().name().equals(item.getType().name()) && existing.amount() < existing.maxStackSize()) {
                    int canAdd = existing.maxStackSize() - existing.amount();
                    int toAdd = Math.min(remaining, canAdd);
                    inv().setItemStack(i, existing.withAmount(existing.amount() + toAdd));
                    remaining -= toAdd;
                }
            }
            for (int i = 0; i < 36 && remaining > 0; i++) {
                ItemStack existing = inv().getItemStack(i);
                if (existing.isAir()) {
                    int toAdd = Math.min(remaining, item.getType().getMaxStackSize());
                    ItemStack msItem = convertBack(item);
                    inv().setItemStack(i, msItem.withAmount(toAdd));
                    bukkitItems.put(i, item);
                    remaining -= toAdd;
                }
            }
            if (remaining > 0) {
                overflow.put(-1, new org.bukkit.inventory.ItemStack(item.getType(), remaining));
            }
        }
        return overflow;
    }

    @Override
    public int firstEmpty() {
        for (int i = 0; i < 36; i++) {
            if (inv().getItemStack(i).isAir()) return i;
        }
        return -1;
    }

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
    public org.bukkit.inventory.InventoryType getType() { return org.bukkit.inventory.InventoryType.PLAYER; }
}
