package me.wildmaster84.adapter.inventory;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import java.util.*;

public class SimpleItemMeta implements ItemMeta, org.bukkit.inventory.meta.SkullMeta, org.bukkit.inventory.meta.BlockStateMeta, org.bukkit.inventory.meta.Damageable, org.bukkit.inventory.meta.Repairable, org.bukkit.inventory.meta.EnchantmentStorageMeta, org.bukkit.inventory.meta.PotionMeta, org.bukkit.inventory.meta.BookMeta, org.bukkit.inventory.meta.LeatherArmorMeta, org.bukkit.inventory.meta.ArmorMeta, org.bukkit.inventory.meta.BannerMeta, org.bukkit.inventory.meta.ShieldMeta, org.bukkit.inventory.meta.SpawnEggMeta {
    private String displayName = "";
    private net.kyori.adventure.text.Component displayNameComponent = null;
    private String localizedName = null;
    private List<String> lore = null;
    private List<net.kyori.adventure.text.Component> loreComponents = null;
    private int customModelData = 0;
    private boolean hasCustomModelDataFlag = false;
    private int repairCost = 0;
    private boolean hasRepairCostFlag = false;
    private boolean unbreakable = false;
    private Set<ItemFlag> itemFlags = new HashSet<>();
    private com.google.common.collect.Multimap<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> attrModifiers = com.google.common.collect.LinkedListMultimap.create();
    private Map<Enchantment, Integer> enchants = new HashMap<>();

    @Override public boolean hasDisplayName() { return displayName != null || displayNameComponent != null; }
    @Override public String getDisplayName() { return displayName != null ? displayName : ""; }
    @Override public void setDisplayName(String name) { this.displayName = name; }
    @Override public net.kyori.adventure.text.Component displayName() { return displayNameComponent; }
    @Override public void displayName(net.kyori.adventure.text.Component displayName) { this.displayNameComponent = displayName; }
    @Override public boolean hasLocalizedName() { return localizedName != null; }
    @Override public String getLocalizedName() { return localizedName; }
    @Override public void setLocalizedName(String name) { this.localizedName = name; }
    @Override public boolean hasLore() { return (lore != null && !lore.isEmpty()) || (loreComponents != null && !loreComponents.isEmpty()); }
    @Override public List<String> getLore() { return lore; }
    @Override public void setLore(List<String> lore) { this.lore = lore; }
    @Override public void lore(List<net.kyori.adventure.text.Component> lore) { this.loreComponents = lore; }
    @Override public List<net.kyori.adventure.text.Component> lore() { return loreComponents; }
    @Override public boolean hasCustomModelData() { return hasCustomModelDataFlag; }
    @Override public int getCustomModelData() { return customModelData; }
    @Override public void setCustomModelData(Integer data) { this.customModelData = data != null ? data : 0; this.hasCustomModelDataFlag = true; }
    @Override public boolean hasEnchants() { return !enchants.isEmpty(); }
    @Override public boolean hasRepairCost() { return hasRepairCostFlag; }
    @Override public int getRepairCost() { return repairCost; }
    @Override public void setRepairCost(int cost) { this.repairCost = cost; this.hasRepairCostFlag = true; }
    @Override public boolean hasAttributeModifiers() { return !attrModifiers.isEmpty(); }
    @Override public boolean addAttributeModifier(org.bukkit.attribute.Attribute attr, org.bukkit.attribute.AttributeModifier mod) { attrModifiers.put(attr, mod); return true; }
    @Override public boolean removeAttributeModifier(org.bukkit.attribute.Attribute attr) { attrModifiers.removeAll(attr); return true; }
    @Override public com.google.common.collect.Multimap<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> getAttributeModifiers() { return attrModifiers; }
    @Override public void setAttributeModifiers(com.google.common.collect.Multimap<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> attributeModifiers) { this.attrModifiers = attributeModifiers; }
    @Override public void setUnbreakable(boolean unbreakable) { this.unbreakable = unbreakable; }
    @Override public boolean isUnbreakable() { return unbreakable; }
    @Override public void addItemFlags(ItemFlag... itemFlags) { this.itemFlags.addAll(Arrays.asList(itemFlags)); }
    @Override public void removeItemFlags(ItemFlag... itemFlags) { this.itemFlags.removeAll(Arrays.asList(itemFlags)); }
    @Override public Set<ItemFlag> getItemFlags() { return itemFlags; }
    @Override public boolean hasCustomTag() { return false; }
    @Override public int getEnchantLevel(Enchantment ench) { return enchants.getOrDefault(ench, 0); }
    @Override public Map<Enchantment, Integer> getEnchants() { return enchants; }
    @Override public boolean removeEnchant(Enchantment ench) { return enchants.remove(ench) != null; }
    @Override public void removeEnchantments() { enchants.clear(); }
    @Override public Map<String, Object> serialize() { return new HashMap<>(); }
    @Override public SimpleItemMeta clone() {
        SimpleItemMeta copy = new SimpleItemMeta();
        copy.displayName = displayName;
        copy.displayNameComponent = displayNameComponent;
        copy.localizedName = localizedName;
        copy.lore = lore != null ? new ArrayList<>(lore) : null;
        copy.loreComponents = loreComponents != null ? new ArrayList<>(loreComponents) : null;
        copy.customModelData = customModelData;
        copy.hasCustomModelDataFlag = hasCustomModelDataFlag;
        copy.repairCost = repairCost;
        copy.hasRepairCostFlag = hasRepairCostFlag;
        copy.unbreakable = unbreakable;
        copy.itemFlags = new HashSet<>(itemFlags);
        copy.attrModifiers = com.google.common.collect.LinkedListMultimap.create(attrModifiers);
        copy.enchants = new HashMap<>(enchants);
        return copy;
    }

    @Override public int getDamage() { return 0; }
    @Override public void setDamage(int damage) { }
    @Override public boolean hasDamage() { return false; }
    @Override public String getOwner() { return null; }
    @Override public boolean setOwner(String owner) { return false; }
    @Override public org.bukkit.OfflinePlayer getOwningPlayer() { return null; }
    @Override public boolean setOwningPlayer(org.bukkit.OfflinePlayer player) { return false; }
    @Override public boolean hasOwner() { return false; }
    @Override public org.bukkit.block.BlockState getBlockState() { return null; }
    @Override public void setBlockState(org.bukkit.block.BlockState blockState) { }
    @Override public boolean hasBlockState() { return false; }
    @Override public boolean addStoredEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) { return false; }
    @Override public boolean removeStoredEnchant(Enchantment ench) { return false; }
    @Override public boolean hasStoredEnchants() { return false; }
    @Override public boolean hasStoredEnchant(Enchantment ench) { return false; }
    @Override public int getStoredEnchantLevel(Enchantment ench) { return 0; }
    @Override public Map<Enchantment, Integer> getStoredEnchants() { return new HashMap<>(); }
    @Override public boolean addEnchantments(Map<Enchantment, Integer> enchantments) { return false; }
    @Override public boolean addCustomEffect(org.bukkit.potion.PotionEffect effect, boolean overwrite) { return false; }
    @Override public boolean addCustomEffect(org.bukkit.potion.PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean overwrite) { return false; }
    @Override public boolean hasCustomEffects() { return false; }
    @Override public List<org.bukkit.potion.PotionEffect> getCustomEffects() { return new ArrayList<>(); }
    @Override public boolean hasCustomEffect(org.bukkit.potion.PotionEffectType type) { return false; }
    @Override public boolean removeCustomEffect(org.bukkit.potion.PotionEffectType type) { return false; }
    @Override public boolean clearCustomEffects() { return false; }
    @Override public boolean hasColor() { return false; }
    @Override public org.bukkit.Color getColor() { return null; }
    @Override public void setColor(org.bukkit.Color color) { }
    @Override public org.bukkit.inventory.meta.PotionMeta.PotionData getPotionData() { return null; }
    @Override public void setPotionData(org.bukkit.inventory.meta.PotionMeta.PotionData data) { }
    @Override public org.bukkit.potion.PotionType getBasePotionType() { return null; }
    @Override public void setBasePotionType(org.bukkit.potion.PotionType type) { }
    @Override public boolean hasTitle() { return false; }
    @Override public String getTitle() { return ""; }
    @Override public boolean setTitle(String title) { return false; }
    @Override public boolean hasAuthor() { return false; }
    @Override public String getAuthor() { return ""; }
    @Override public void setAuthor(String author) { }
    @Override public boolean hasGeneration() { return false; }
    @Override public org.bukkit.inventory.meta.BookMeta.Generation getGeneration() { return null; }
    @Override public void setGeneration(org.bukkit.inventory.meta.BookMeta.Generation generation) { }
    @Override public boolean hasPages() { return false; }
    @Override public List<String> getPages() { return new ArrayList<>(); }
    @Override public void setPages(List<String> pages) { }
    @Override public void setPages(String... pages) { }
    @Override public void addPage(String... pages) { }
    @Override public int getPageCount() { return 0; }
    @Override public boolean hasItemMeta() { return false; }
    @Override public boolean isDyed() { return false; }
    @Override public boolean hasTrim() { return false; }
    @Override public void setTrim(Object trim) { }
    @Override public Object getTrim() { return null; }
    @Override public org.bukkit.DyeColor getBaseColor() { return null; }
    @Override public void setBaseColor(org.bukkit.DyeColor color) { }
    @Override public List<org.bukkit.block.banner.Pattern> getPatterns() { return new ArrayList<>(); }
    @Override public void setPatterns(List<org.bukkit.block.banner.Pattern> patterns) { }
    @Override public void addPattern(org.bukkit.block.banner.Pattern pattern) { }
    @Override public org.bukkit.block.banner.Pattern getPattern(int i) { return null; }
    @Override public int numberOfPatterns() { return 0; }
    @Override public void removePattern(int i) { }
    @Override public boolean hasBaseColor() { return false; }
    @Override public org.bukkit.entity.EntityType getCustomSpawnedType() { return null; }
    @Override public void setCustomSpawnedType(org.bukkit.entity.EntityType type) { }
    @Override public org.bukkit.entity.EntityType getSpawnedType() { return null; }
    @Override public void setSpawnedType(org.bukkit.entity.EntityType type) { }
}
