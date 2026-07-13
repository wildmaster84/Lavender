package org.bukkit.entity;

public enum EntityType {
    DROPPED_ITEM, EXPERIENCE_ORB, AREA_EFFECT_CLOUD, ELDER_GUARDIAN, WITHER_SKELETON, WITHER,
    WITHER_SKULL, EYE_OF_ENDER, FIREWORK, ITEM_FRAME, PAINTING, LEASH_HITCH, ARMOR_STAND,
    EVOKER, VEX, VINDICATOR, ILLUSIONER, COMMAND_MINECART, BOAT, MINECART, MINECART_CHEST,
    MINECART_COMMAND, MINECART_FURNACE, MINECART_TNT, MINECART_HOPPER, MINECART_MOB_SPAWNER,
    CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER,
    SILVERFISH, BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITCH, ENDERMITE, GUARDIAN, SHULKER,
    PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE,
    RABBIT, POLAR_BEAR, LLAMA, PARROT, VILLAGER, ENDER_CRYSTAL, TURTLE, PHANTOM, TRIDENT,
    COD, SALMON, PUFFERFISH, TROPICAL_FISH, DROWNED, DOLPHIN, CAT, PANDA, PILLAGER, RAVAGER,
    FOX, BEE, HOGLIN, PIGLIN, STRIDER, ZOGLIN, ZOMBIFIED_PIGLIN, STRAY, HUSK, DONKEY, MULE,
    SKELETON_HORSE, ZOMBIE_HORSE, EVOKER_FANGS, FISHING_HOOK, LIGHTNING, PLAYER, UNKNOWN,
    DRAGON_FIREBALL, EGG, ENDER_PEARL, ENDER_SIGNAL, FIREBALL,
    SMALL_FIREBALL, SNOWBALL, SPECTRAL_ARROW, TIPPED_ARROW, WIND_CHARGE,
    ARROW, ALLAY, AXOLOTL, BAT, BLOCK_DISPLAY, CAMEL, CHEST_BOAT, ITEM_DISPLAY,
    GLOW_ITEM_FRAME, GLOW_SQUID, GOAT, INTERACTION, MARKER, MOOSHROOM,
    OAK_BOAT, OAK_CHEST_BOAT, PIGLIN_BRUTE, TEXT_DISPLAY, TADPOLE, WARDEN,
    WANDERING_TRADER, FROG;

    public String getName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public String getKey() {
        return "minecraft:" + name().toLowerCase(java.util.Locale.ROOT);
    }

    public static EntityType fromName(String name) {
        if (name == null) return null;
        try {
            return EntityType.valueOf(name.toUpperCase(java.util.Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    public static EntityType fromKey(String key) {
        if (key == null) return null;
        String stripped = key.replace("minecraft:", "");
        return fromName(stripped);
    }

    public boolean isAlive() {
        switch (this) {
            case ARMOR_STAND: return false;
            default: return true;
        }
    }

    public boolean isSpawnable() {
        return this != UNKNOWN;
    }
}
