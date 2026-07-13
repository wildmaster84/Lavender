package org.bukkit.inventory.meta;

public interface ArmorMeta extends ItemMeta {
    boolean hasTrim();
    void setTrim(Object trim);
    Object getTrim();
}
