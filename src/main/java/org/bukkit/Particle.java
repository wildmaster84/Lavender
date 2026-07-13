package org.bukkit;

public enum Particle {
    EXPLOSION_NORMAL,
    EXPLOSION_LARGE,
    EXPLOSION_HUGE,
    FIREWORKS_SPARK,
    WATER_BUBBLE,
    WATER_SPLASH,
    WATER_WAKE,
    SUSPENDED,
    SUSPENDED_DEPTH,
    CRIT,
    CRIT_MAGIC,
    SMOKE_NORMAL,
    SMOKE_LARGE,
    SPELL,
    SPELL_INSTANT,
    SPELL_MOB,
    SPELL_MOB_AMBIENT,
    SPELL_WITCH,
    DRIP_WATER,
    DRIP_LAVA,
    VILLAGER_ANGRY,
    VILLAGER_HAPPY,
    TOWN_AURA,
    NOTE,
    PORTAL,
    ENCHANTMENT_TABLE,
    FLAME,
    LAVA,
    FOOTSTEP,
    CLOUD,
    REDSTONE,
    SNOWBALL,
    SNOW_SHOVEL,
    SLIME,
    HEART,
    BARRIER,
    ITEM_CRACK,
    BLOCK_CRACK,
    BLOCK_DUST,
    WATER_DROP,
    ITEM_TAKE,
    MOB_APPEARANCE,
    SQUID_INK,
    BUBBLE_POP,
    BUBBLE_COLUMN_UP,
    NAUTILUS,
    DOLPHIN,
    SNEEZE,
    CAMPFIRE_COSY_SMOKE,
    CAMPFIRE_SIGNAL_SMOKE,
    COMPOSTER,
    FLASH,
    FALLING_LAVA,
    LANDING_LAVA,
    DRIPPING_WATER,
    DRIPPING_LAVA,
    DRIPPING_HONEY,
    FALLING_HONEY,
    LANDING_HONEY,
    FALLING_NECTAR,
    DRIPPING_OBSIDIAN_TEAR,
    FALLING_OBSIDIAN_TEAR,
    LANDING_OBSIDIAN_TEAR,
    REVERSE_PORTAL,
    WHITE_ASH,
    ASH,
    CRIMSON_SPORE,
    WARPED_SPORE,
    SOUL_FIRE_FLAME,
    SOUL,
    DRAGON_BREATH,
    END_ROD,
    SPIT,
    TOTEM,
    MYCELIUM,
    FURNACE_FLAME;

    public Class<?> getDataType() {
        switch (this) {
            case REDSTONE: return DustOptions.class;
            case ITEM_CRACK: return org.bukkit.inventory.ItemStack.class;
            case BLOCK_CRACK:
            case BLOCK_DUST: return org.bukkit.block.data.BlockData.class;
            default: return Void.class;
        }
    }

    public static class DustOptions {
        private final org.bukkit.Color color;
        private final float size;

        public DustOptions(org.bukkit.Color color, float size) {
            this.color = color;
            this.size = size;
        }

        public org.bukkit.Color getColor() { return color; }
        public float getSize() { return size; }
    }
}
