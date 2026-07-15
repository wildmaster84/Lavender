package net.minecraft.world.level.block.state.properties;

public class BlockStateProperties {
    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty SNOWY = BooleanProperty.create("snowy");
    public static final BooleanProperty INVERTED = BooleanProperty.create("inverted");
    public static final BooleanProperty IN_WALL = BooleanProperty.create("in_wall");
    public static final BooleanProperty ATTACHED = BooleanProperty.create("attached");
    public static final BooleanProperty DISARMED = BooleanProperty.create("disarmed");
    public static final BooleanProperty DRAG_DOWN = BooleanProperty.create("drag_down");
    public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");
    public static final BooleanProperty EYE = BooleanProperty.create("eye");
    public static final BooleanProperty FALLING = BooleanProperty.create("falling");
    public static final BooleanProperty HANGING = BooleanProperty.create("hanging");
    public static final BooleanProperty HAS_BREWING_RECIPE = BooleanProperty.create("has_brewing_recipe");
    public static final BooleanProperty HAS_RECORD = BooleanProperty.create("has_record");
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final BooleanProperty OCCUPY = BooleanProperty.create("occupy");
    public static final BooleanProperty PERSISTENT = BooleanProperty.create("persistent");
    public static final BooleanProperty POWERED_RAIL = BooleanProperty.create("powered_rail");
    public static final BooleanProperty SHORT = BooleanProperty.create("short");
    public static final BooleanProperty SIGNAL_FIRE = BooleanProperty.create("signal_fire");
    public static final BooleanProperty SLOT_OCCUPIED = BooleanProperty.create("slot_occupied");
    public static final BooleanProperty SUSPENDED = BooleanProperty.create("suspended");
    public static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");
    public static final BooleanProperty UNSTABLE = BooleanProperty.create("unstable");
    public static final BooleanProperty WATER = BooleanProperty.create("water");
    public static final BooleanProperty BLOOM = BooleanProperty.create("bloom");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final BooleanProperty CONDITIONAL = BooleanProperty.create("conditional");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public static final IntegerProperty AGE_1 = IntegerProperty.create("age", 0, 1);
    public static final IntegerProperty AGE_2 = IntegerProperty.create("age", 0, 2);
    public static final IntegerProperty AGE_3 = IntegerProperty.create("age", 0, 3);
    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);
    public static final IntegerProperty AGE_5 = IntegerProperty.create("age", 0, 5);
    public static final IntegerProperty AGE_7 = IntegerProperty.create("age", 0, 7);
    public static final IntegerProperty AGE_15 = IntegerProperty.create("age", 0, 15);
    public static final IntegerProperty AGE_25 = IntegerProperty.create("age", 0, 25);
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 15);
    public static final IntegerProperty LEVEL_HONEY = IntegerProperty.create("honey_level", 0, 5);
    public static final IntegerProperty LEVEL_COMPOSTER = IntegerProperty.create("level", 0, 8);
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 1);
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 7);
    public static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 4);
    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 2);
    public static final IntegerProperty PICKLES = IntegerProperty.create("pickles", 1, 4);
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 6);
    public static final IntegerProperty DELAY = IntegerProperty.create("delay", 0, 15);
    public static final IntegerProperty NOTES = IntegerProperty.create("note", 0, 24);
    public static final IntegerProperty ROTATION_16 = IntegerProperty.create("rotation", 0, 15);
    public static final IntegerProperty FLOWER_AMOUNT = IntegerProperty.create("flower_amount", 1, 4);

    public enum FaceSearchPosition {
        ABOVE,
        BELOW,
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}
