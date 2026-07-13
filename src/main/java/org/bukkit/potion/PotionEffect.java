package org.bukkit.potion;

public class PotionEffect {
    public PotionEffect(PotionEffectType type, int duration, int amplifier) {}
    public PotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {}
    public PotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {}
    public PotionEffectType getType() { return null; }
    public int getDuration() { return 0; }
    public int getAmplifier() { return 0; }
    public boolean isAmbient() { return false; }
    public boolean hasParticles() { return false; }
    public boolean hasIcon() { return false; }
    public boolean apply(org.bukkit.entity.LivingEntity entity) { return false; }
    public java.util.Map<String, Object> serialize() { return new java.util.HashMap<>(); }
}
