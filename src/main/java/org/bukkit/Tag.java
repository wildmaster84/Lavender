package org.bukkit;

import java.util.Set;
import java.util.Collections;

public interface Tag<T extends Keyed> {
    NamespacedKey getKey();
    boolean isTagged(T item);
    Set<T> getValues();
    String getName();

    Tag<Material> ACACIA_LOGS = new SimpleTag("acacia_logs");
    Tag<Material> ALL_SIGNS = new SimpleTag("all_signs");
    Tag<Material> ANVIL = new SimpleTag("anvil");
    Tag<Material> AZALEA_ROOT_REPLACEABLE = new SimpleTag("azalea_root_replaceable");
    Tag<Material> BANNERS = new SimpleTag("banners");
    Tag<Material> BARS = new SimpleTag("bars");
    Tag<Material> BASE_STONE_NETHER = new SimpleTag("base_stone_nether");
    Tag<Material> BASE_STONE_OVERWORLD = new SimpleTag("base_stone_overworld");
    Tag<Material> BEACON_BASE_BLOCKS = new SimpleTag("beacon_base_blocks");
    Tag<Material> BEDS = new SimpleTag("beds");
    Tag<Material> BEEHIVES = new SimpleTag("beehives");
    Tag<Material> BIG_DRIPLEAF_TILTS = new SimpleTag("big_dripleaf_tilts");
    Tag<Material> BUTTONS = new SimpleTag("buttons");
    Tag<Material> CANDLE_CAKES = new SimpleTag("candle_cakes");
    Tag<Material> CANDLES = new SimpleTag("candles");
    Tag<Material> CAULDRONS = new SimpleTag("cauldrons");
    Tag<Material> CAVE_VINES = new SimpleTag("cave_vines");
    Tag<Material> CEILING_HANGING_SIGNS = new SimpleTag("ceiling_hanging_signs");
    Tag<Material> CHAINS = new SimpleTag("chains");
    Tag<Material> COAL_ORES = new SimpleTag("coal_ores");
    Tag<Material> COMBINATION_STEP_SOUND_BLOCKS = new SimpleTag("combination_step_sound_blocks");
    Tag<Material> CONCRETE_POWDER = new SimpleTag("concrete_powder");
    Tag<Material> CONVERTABLE_TO_MUD = new SimpleTag("convertable_to_mud");
    Tag<Material> COPPER_ORES = new SimpleTag("copper_ores");
    Tag<Material> CORAL_BLOCKS = new SimpleTag("coral_blocks");
    Tag<Material> CORAL_PLANTS = new SimpleTag("coral_plants");
    Tag<Material> CORALS = new SimpleTag("corals");
    Tag<Material> CROPS = new SimpleTag("crops");
    Tag<Material> CRYSTAL_SOUND_BLOCKS = new SimpleTag("crystal_sound_blocks");
    Tag<Material> DAMAGES_CAMEL = new SimpleTag("damages_camel");
    Tag<Material> DEEPSLATE_ORE_REPLACEABLES = new SimpleTag("deepslate_ore_replaceables");
    Tag<Material> DIAMOND_ORES = new SimpleTag("diamond_ores");
    Tag<Material> DIRT = new SimpleTag("dirt");
    Tag<Material> DOORS = new SimpleTag("doors");
    Tag<Material> DRAGON_IMMUNE = new SimpleTag("dragon_immune");
    Tag<Material> DRIPSTONE_REPLACEABLE = new SimpleTag("dripstone_replaceable");
    Tag<Material> EMERALD_ORES = new SimpleTag("emerald_ores");
    Tag<Material> ENCHANTMENT_POWER_PROVIDER = new SimpleTag("enchantment_power_provider");
    Tag<Material> ENCHANTMENT_POWER_TRANSMITTER = new SimpleTag("enchantment_power_transmitter");
    Tag<Material> FENCE_GATES = new SimpleTag("fence_gates");
    Tag<Material> FENCES = new SimpleTag("fences");
    Tag<Material> FIRE = new SimpleTag("fire");
    Tag<Material> FLOWER_POTS = new SimpleTag("flower_pots");
    Tag<Material> FLOWERS = new SimpleTag("flowers");
    Tag<Material> FOX_FOOD = new SimpleTag("fox_food");
    Tag<Material> FEATURES_CANNOT_REPLACE = new SimpleTag("features_cannot_replace");
    Tag<Material> FROG_PREFER_JUMP_TO = new SimpleTag("frog_prefer_jump_to");
    Tag<Material> GOLD_ORES = new SimpleTag("gold_ores");
    Tag<Material> GUARDED_BY_PIGLINS = new SimpleTag("guarded_by_piglins");
    Tag<Material> HOGLIN_REPELLENTS = new SimpleTag("hoglin_repellents");
    Tag<Material> ICE = new SimpleTag("ice");
    Tag<Material> IMPERMEABLE = new SimpleTag("impermeable");
    Tag<Material> INFINIBURN_END = new SimpleTag("infiniburn_end");
    Tag<Material> INFINIBURN_NETHER = new SimpleTag("infiniburn_nether");
    Tag<Material> INFINIBURN_OVERWORLD = new SimpleTag("infiniburn_overworld");
    Tag<Material> INSIDE_STEP_SOUND_BLOCKS = new SimpleTag("inside_step_sound_blocks");
    Tag<Material> IRON_ORES = new SimpleTag("iron_ores");
    Tag<Material> ITEMS_AIR = new SimpleTag("items_air");
    Tag<Material> JUNGLE_LOGS = new SimpleTag("jungle_logs");
    Tag<Material> LANTERNS = new SimpleTag("lanterns");
    Tag<Material> LAPIS_ORES = new SimpleTag("lapis_ores");
    Tag<Material> LAVA_POOL_STONE_CANNOT_REPLACE = new SimpleTag("lava_pool_stone_cannot_replace");
    Tag<Material> LEAVES = new SimpleTag("leaves");
    Tag<Material> LIGHTNING_RODS = new SimpleTag("lightning_rods");
    Tag<Material> LOGS = new SimpleTag("logs");
    Tag<Material> LOGS_THAT_BURN = new SimpleTag("logs_that_burn");
    Tag<Material> LUSH_GROUND_REPLACEABLE = new SimpleTag("lush_ground_replaceable");
    Tag<Material> MANGROVE_LOGS = new SimpleTag("mangrove_logs");
    Tag<Material> MINECRAFT_ARMOR_TICKABLE = new SimpleTag("minecraft_armor_tickable");
    Tag<Material> MOOSHROOMS_SPAWNABLE_ON = new SimpleTag("mooshrooms_spawnable_on");
    Tag<Material> MOSS_REPLACEABLE = new SimpleTag("moss_replaceable");
    Tag<Material> MUSHROOM_GROW_BLOCK = new SimpleTag("mushroom_grow_block");
    Tag<Material> NEEDS_DIAMOND_TOOL = new SimpleTag("needs_diamond_tool");
    Tag<Material> NEEDS_IRON_TOOL = new SimpleTag("needs_iron_tool");
    Tag<Material> NEEDS_STONE_TOOL = new SimpleTag("needs_stone_tool");
    Tag<Material> NETHER_CARVER_REPLACEABLE = new SimpleTag("nether_carver_replaceable");
    Tag<Material> NYLIUM = new SimpleTag("nylium");
    Tag<Material> OAK_LOGS = new SimpleTag("oak_logs");
    Tag<Material> OCCLUDES_VIBRATION_SIGNALS = new SimpleTag("occludes_vibration_signals");
    Tag<Material> OVERWORLD_CARVER_REPLACEABLE = new SimpleTag("overworld_carver_replaceable");
    Tag<Material> OVERWORLD_NATURAL_LOGS = new SimpleTag("overworld_natural_logs");
    Tag<Material> PANDA_SPAWNABLE_ON = new SimpleTag("panda_spawnable_on");
    Tag<Material> PARROTS_SPAWNABLE_ON = new SimpleTag("parrots_spawnable_on");
    Tag<Material> PIGLIN_REPELLENTS = new SimpleTag("piglin_repellents");
    Tag<Material> PLANKS = new SimpleTag("planks");
    Tag<Material> POLAR_BEARS_SPAWNABLE_ON_ALTERNATE = new SimpleTag("polar_bears_spawnable_on_alternate");
    Tag<Material> PORTALS = new SimpleTag("portals");
    Tag<Material> PRESSURE_PLATES = new SimpleTag("pressure_plates");
    Tag<Material> PREVENT_MOB_SPAWNING_INSIDE = new SimpleTag("prevent_mob_spawning_inside");
    Tag<Material> RABBITS_SPAWNABLE_ON = new SimpleTag("rabbits_spawnable_on");
    Tag<Material> RAILS = new SimpleTag("rails");
    Tag<Material> REDSTONE_ORES = new SimpleTag("redstone_ores");
    Tag<Material> REPLACEABLE = new SimpleTag("replaceable");
    Tag<Material> REPLACEABLE_PLANTS = new SimpleTag("replaceable_plants");
    Tag<Material> SAND = new SimpleTag("sand");
    Tag<Material> SAPLINGS = new SimpleTag("saplings");
    Tag<Material> SCULK_REPLACEABLE = new SimpleTag("sculk_replaceable");
    Tag<Material> SHULKER_BOXES = new SimpleTag("shulker_boxes");
    Tag<Material> SIGNS = new SimpleTag("signs");
    Tag<Material> SLABS = new SimpleTag("slabs");
    Tag<Material> SMALL_DRIPLEAF_PLACEABLE = new SimpleTag("small_dripleaf_placeable");
    Tag<Material> SMALL_FLOWERS = new SimpleTag("small_flowers");
    Tag<Material> SMELTS_TO_GLASS = new SimpleTag("smelts_to_glass");
    Tag<Material> SNAPS_GOAT_HORN = new SimpleTag("snaps_goat_horn");
    Tag<Material> SNOW = new SimpleTag("snow");
    Tag<Material> SNOW_LAYER_CAN_SURVIVE_ON = new SimpleTag("snow_layer_can_survive_on");
    Tag<Material> SOUL_FIRE_BASE_BLOCKS = new SimpleTag("soul_fire_base_blocks");
    Tag<Material> SPRUCE_LOGS = new SimpleTag("spruce_logs");
    Tag<Material> STAIRS = new SimpleTag("stairs");
    Tag<Material> STANDING_SIGNS = new SimpleTag("standing_signs");
    Tag<Material> STONE_BRICKS = new SimpleTag("stone_bricks");
    Tag<Material> STONE_ORE_REPLACEABLES = new SimpleTag("stone_ore_replaceables");
    Tag<Material> STONE_PRESSURE_PLATES = new SimpleTag("stone_pressure_plates");
    Tag<Material> STRIDER_WARM_BLOCKS = new SimpleTag("strider_warm_blocks");
    Tag<Material> SWORD_EFFICIENT = new SimpleTag("sword_efficient");
    Tag<Material> TALL_FLOWERS = new SimpleTag("tall_flowers");
    Tag<Material> TERRACOTTA = new SimpleTag("terracotta");
    Tag<Material> TRAPDOORS = new SimpleTag("trapdoors");
    Tag<Material> UNDERWATER_BONEMEALS = new SimpleTag("underwater_bonemeals");
    Tag<Material> UNSTABLE_BOTTOM_CENTER = new SimpleTag("unstable_bottom_center");
    Tag<Material> VIBRATION_RESONANCE_BLOCKS = new SimpleTag("vibration_resonance_blocks");
    Tag<Material> WALL_CORALS = new SimpleTag("wall_corals");
    Tag<Material> WALL_POST_OVERRIDE = new SimpleTag("wall_post_override");
    Tag<Material> WALL_SIGNS = new SimpleTag("wall_signs");
    Tag<Material> WALLS = new SimpleTag("walls");
    Tag<Material> WART_BLOCKS = new SimpleTag("wart_blocks");
    Tag<Material> WITHER_SUMMON_BASE_BLOCKS = new SimpleTag("wither_summon_base_blocks");
    Tag<Material> WOODEN_BUTTONS = new SimpleTag("wooden_buttons");
    Tag<Material> WOODEN_DOORS = new SimpleTag("wooden_doors");
    Tag<Material> WOODEN_FENCES = new SimpleTag("wooden_fences");
    Tag<Material> WOODEN_PRESSURE_PLATES = new SimpleTag("wooden_pressure_plates");
    Tag<Material> WOODEN_SHELVES = new SimpleTag("wooden_shelves");
    Tag<Material> WOODEN_TRAPDOORS = new SimpleTag("wooden_trapdoors");
    Tag<Material> WOOL = new SimpleTag("wool");
    Tag<Material> WOOL_CARPETS = new SimpleTag("wool_carpets");

    // WorldGuard 7.0.17 specific tags
    Tag<Material> COPPER_CHESTS = new SimpleTag("copper_chests");
    Tag<Material> COPPER_GOLEM_STATUES = new SimpleTag("copper_golem_statues");
    Tag<Material> ITEMS_BANNERS = new SimpleTag("items_banners");
    Tag<Material> ITEMS_BOATS = new SimpleTag("items_boats");
    Tag<Material> ITEMS_BUNDLES = new SimpleTag("items_bundles");
    Tag<Material> ITEMS_CHEST_ARMOR = new SimpleTag("items_chest_armor");
    Tag<Material> ITEMS_CHEST_BOATS = new SimpleTag("items_chest_boats");
    Tag<Material> ITEMS_DECORATED_POT_SHERDS = new SimpleTag("items_decorated_pot_sherds");
    Tag<Material> ITEMS_FOOT_ARMOR = new SimpleTag("items_foot_armor");
    Tag<Material> ITEMS_HARNESSES = new SimpleTag("items_harnesses");
    Tag<Material> ITEMS_HEAD_ARMOR = new SimpleTag("items_head_armor");
    Tag<Material> ITEMS_LEG_ARMOR = new SimpleTag("items_leg_armor");
    Tag<Material> ITEMS_SKULLS = new SimpleTag("items_skulls");
    Tag<Material> ITEMS_SPEARS = new SimpleTag("items_spears");

    class SimpleTag implements Tag<Material> {
        private final NamespacedKey key;
        private final String name;

        SimpleTag(String name) {
            this.key = new NamespacedKey("minecraft", name);
            this.name = name;
        }

        @Override
        public NamespacedKey getKey() { return key; }

        @Override
        public boolean isTagged(Material item) { return false; }

        @Override
        public Set<Material> getValues() { return Collections.emptySet(); }

        @Override
        public String getName() { return name; }
    }
}
