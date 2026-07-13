package org.bukkit.inventory.meta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.List;

public interface PotionMeta extends ItemMeta {
    boolean addCustomEffect(PotionEffect effect, boolean overwrite);
    boolean addCustomEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean overwrite);
    boolean hasCustomEffects();
    List<PotionEffect> getCustomEffects();
    boolean hasCustomEffect(PotionEffectType type);
    boolean removeCustomEffect(PotionEffectType type);
    boolean clearCustomEffects();
    boolean hasColor();
    org.bukkit.Color getColor();
    void setColor(org.bukkit.Color color);
    PotionData getPotionData();
    void setPotionData(PotionData data);
    org.bukkit.potion.PotionType getBasePotionType();
    void setBasePotionType(org.bukkit.potion.PotionType type);

    class PotionData {
        private final org.bukkit.potion.PotionType type;
        private final boolean extended;
        private final boolean upgraded;
        public PotionData(org.bukkit.potion.PotionType type, boolean extended, boolean upgraded) {
            this.type = type; this.extended = extended; this.upgraded = upgraded;
        }
        public org.bukkit.potion.PotionType getType() { return type; }
        public boolean isExtended() { return extended; }
        public boolean isUpgraded() { return upgraded; }
    }
}
