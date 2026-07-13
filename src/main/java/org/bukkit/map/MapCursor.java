package org.bukkit.map;

public final class MapCursor {

    public enum Type {
        WHITE_POINTER(0),
        GREEN_POINTER(1),
        RED_POINTER(2),
        BLUE_POINTER(3),
        WHITE_CROSS(4),
        RED_MARKER(5),
        WHITE_CIRCLE(6),
        SMALL_WHITE_CIRCLE(7),
        MANSION(8),
        TEMPLE(9),
        WHITE_BANNER(10),
        ORANGE_BANNER(11),
        MAGENTA_BANNER(12),
        LIGHT_BLUE_BANNER(13),
        YELLOW_BANNER(14),
        LIME_BANNER(15),
        PINK_BANNER(16),
        GRAY_BANNER(17),
        LIGHT_GRAY_BANNER(18),
        CYAN_BANNER(19),
        PURPLE_BANNER(20),
        BLUE_BANNER(21),
        BROWN_BANNER(22),
        GREEN_BANNER(23),
        RED_BANNER(24),
        BLACK_BANNER(25),
        RED_X(26),
        DESERT_VILLAGE(27),
        PLAINS_VILLAGE(28),
        SAVANNA_VILLAGE(29),
        SNOWY_VILLAGE(30),
        TAIGA_VILLAGE(31),
        JUNGLE_TEMPLE(32),
        SWAMP_HUT(33),
        TRIAL_CHAMBERS(34),
        BURIED_TREASURE(35);

        private final byte value;
        Type(int value) { this.value = (byte) value; }
        public byte getValue() { return value; }
    }

    public enum Direction {
        RIGHT(0),
        UP_RIGHT(1),
        UP(2),
        UP_LEFT(3),
        LEFT(4),
        DOWN_LEFT(5),
        DOWN(6),
        DOWN_RIGHT(7);

        private final byte value;
        Direction(int value) { this.value = (byte) value; }
        public byte getValue() { return value; }
    }

    public MapCursor(int x, int z, byte direction, byte type) {}
    public MapCursor(int x, int z, byte direction, byte type, boolean visible) {}
    public MapCursor(int x, int z, byte direction, byte type, boolean visible, String caption) {}

    public int getX() { return 0; }
    public int getZ() { return 0; }
    public byte getDirection() { return 0; }
    public byte getType() { return 0; }
    public boolean isVisible() { return false; }
    public String getCaption() { return null; }
    public void setX(int x) {}
    public void setZ(int z) {}
    public void setDirection(byte direction) {}
    public void setType(byte type) {}
    public void setVisible(boolean visible) {}
    public void setCaption(String caption) {}
    public boolean isRaw() { return false; }
    public void setRaw(boolean raw) {}
}
