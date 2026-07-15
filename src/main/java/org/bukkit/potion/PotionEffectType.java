package org.bukkit.potion;

import java.util.HashMap;
import java.util.Map;

public abstract class PotionEffectType {

    private static final Map<Integer, PotionEffectType> BY_ID = new HashMap<>();
    private static final Map<String, PotionEffectType> BY_NAME = new HashMap<>();
    private final int id;
    private final String name;
    private final boolean instant;
    private final org.bukkit.Color color;

    protected PotionEffectType(int id, String name, boolean instant, org.bukkit.Color color) {
        this.id = id;
        this.name = name;
        this.instant = instant;
        this.color = color;
        BY_ID.put(id, this);
        BY_NAME.put(name.toUpperCase(), this);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isInstant() { return instant; }
    public org.bukkit.Color getColor() { return color; }

    public static PotionEffectType getById(int id) { return BY_ID.get(id); }
    public static PotionEffectType getByName(String name) { return BY_NAME.get(name.toUpperCase()); }
    public static PotionEffectType[] values() { return BY_ID.values().toArray(new PotionEffectType[0]); }

    public static final PotionEffectType SPEED = new Simple(1, "SPEED", false, org.bukkit.Color.fromRGB(0x7CAFC6));
    public static final PotionEffectType SLOWNESS = new Simple(2, "SLOWNESS", false, org.bukkit.Color.fromRGB(0x5A7D99));
    public static final PotionEffectType HASTE = new Simple(3, "HASTE", false, org.bukkit.Color.fromRGB(0xD9C043));
    public static final PotionEffectType MINING_FATIGUE = new Simple(4, "MINING_FATIGUE", false, org.bukkit.Color.fromRGB(0x4A4217));
    public static final PotionEffectType STRENGTH = new Simple(5, "STRENGTH", false, org.bukkit.Color.fromRGB(0x932423));
    public static final PotionEffectType INSTANT_HEALTH = new Simple(6, "INSTANT_HEALTH", true, org.bukkit.Color.fromRGB(0xF82423));
    public static final PotionEffectType INSTANT_DAMAGE = new Simple(7, "INSTANT_DAMAGE", true, org.bukkit.Color.fromRGB(0x430A09));
    public static final PotionEffectType JUMP_BOOST = new Simple(8, "JUMP_BOOST", false, org.bukkit.Color.fromRGB(0x22FF33));
    public static final PotionEffectType NAUSEA = new Simple(9, "NAUSEA", false, org.bukkit.Color.fromRGB(0x551D4A));
    public static final PotionEffectType REGENERATION = new Simple(10, "REGENERATION", false, org.bukkit.Color.fromRGB(0xCD5CAB));
    public static final PotionEffectType RESISTANCE = new Simple(11, "RESISTANCE", false, org.bukkit.Color.fromRGB(0x99453D));
    public static final PotionEffectType FIRE_RESISTANCE = new Simple(12, "FIRE_RESISTANCE", false, org.bukkit.Color.fromRGB(0xE49A3B));
    public static final PotionEffectType WATER_BREATHING = new Simple(13, "WATER_BREATHING", false, org.bukkit.Color.fromRGB(0x2E5299));
    public static final PotionEffectType INVISIBILITY = new Simple(14, "INVISIBILITY", false, org.bukkit.Color.fromRGB(0x7F8392));
    public static final PotionEffectType BLINDNESS = new Simple(15, "BLINDNESS", false, org.bukkit.Color.fromRGB(0x1F1F23));
    public static final PotionEffectType NIGHT_VISION = new Simple(16, "NIGHT_VISION", false, org.bukkit.Color.fromRGB(0x1F1FA1));
    public static final PotionEffectType HUNGER = new Simple(17, "HUNGER", false, org.bukkit.Color.fromRGB(0x587653));
    public static final PotionEffectType WEAKNESS = new Simple(18, "WEAKNESS", false, org.bukkit.Color.fromRGB(0x484D48));
    public static final PotionEffectType POISON = new Simple(19, "POISON", false, org.bukkit.Color.fromRGB(0x4E9331));
    public static final PotionEffectType WITHER = new Simple(20, "WITHER", false, org.bukkit.Color.fromRGB(0x352A27));
    public static final PotionEffectType HEALTH_BOOST = new Simple(21, "HEALTH_BOOST", false, org.bukkit.Color.fromRGB(0xF87D23));
    public static final PotionEffectType ABSORPTION = new Simple(22, "ABSORPTION", false, org.bukkit.Color.fromRGB(0xFFBC8E));
    public static final PotionEffectType SATURATION = new Simple(23, "SATURATION", false, org.bukkit.Color.fromRGB(0xF82423));
    public static final PotionEffectType GLOWING = new Simple(24, "GLOWING", false, org.bukkit.Color.fromRGB(0x94A061));
    public static final PotionEffectType LEVITATION = new Simple(25, "LEVITATION", false, org.bukkit.Color.fromRGB(0xCEFFFF));
    public static final PotionEffectType LUCK = new Simple(26, "LUCK", false, org.bukkit.Color.fromRGB(0x33945A));
    public static final PotionEffectType UNLUCK = new Simple(27, "UNLUCK", false, org.bukkit.Color.fromRGB(0xC0A223));
    public static final PotionEffectType SLOW_FALLING = new Simple(28, "SLOW_FALLING", false, org.bukkit.Color.fromRGB(0xA7A7A7));
    public static final PotionEffectType CONDUIT_POWER = new Simple(29, "CONDUIT_POWER", false, org.bukkit.Color.fromRGB(0x1D6DCE));
    public static final PotionEffectType DOLPHINS_GRACE = new Simple(30, "DOLPHINS_GRACE", false, org.bukkit.Color.fromRGB(0x1D6DCE));
    public static final PotionEffectType BAD_OMEN = new Simple(31, "BAD_OMEN", false, org.bukkit.Color.fromRGB(0x0B4B3B));
    public static final PotionEffectType HERO_OF_THE_VILLAGE = new Simple(32, "HERO_OF_THE_VILLAGE", false, org.bukkit.Color.fromRGB(0x44FF51));
    public static final PotionEffectType DARKNESS = new Simple(33, "DARKNESS", false, org.bukkit.Color.fromRGB(0x0D0D1F));
    public static final PotionEffectType TRIAL_OMEN = new Simple(34, "TRIAL_OMEN", false, org.bukkit.Color.fromRGB(0x996533));
    public static final PotionEffectType RAID_OMEN = new Simple(35, "RAID_OMEN", false, org.bukkit.Color.fromRGB(0x996533));
    public static final PotionEffectType WIND_CHARGED = new Simple(36, "WIND_CHARGED", false, org.bukkit.Color.fromRGB(0x7B7BE3));
    public static final PotionEffectType WEAVING = new Simple(37, "WEAVING", false, org.bukkit.Color.fromRGB(0x4D4D4D));
    public static final PotionEffectType OOZING = new Simple(38, "OOZING", false, org.bukkit.Color.fromRGB(0x7BA343));
    public static final PotionEffectType INFESTED = new Simple(39, "INFESTED", false, org.bukkit.Color.fromRGB(0x8B7355));

    public static final PotionEffectType SLOW = SLOWNESS;
    public static final PotionEffectType FAST_DIGGING = HASTE;
    public static final PotionEffectType SLOW_DIGGING = MINING_FATIGUE;
    public static final PotionEffectType INCREASE_DAMAGE = STRENGTH;
    public static final PotionEffectType HEAL = INSTANT_HEALTH;
    public static final PotionEffectType HARM = INSTANT_DAMAGE;
    public static final PotionEffectType JUMP = JUMP_BOOST;
    public static final PotionEffectType CONFUSION = NAUSEA;
    public static final PotionEffectType DAMAGE_RESISTANCE = RESISTANCE;

    private static class Simple extends PotionEffectType {
        Simple(int id, String name, boolean instant, org.bukkit.Color color) {
            super(id, name, instant, color);
        }
    }
}
