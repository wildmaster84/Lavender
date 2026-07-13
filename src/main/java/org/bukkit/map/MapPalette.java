package org.bukkit.map;

import java.awt.Color;

public final class MapPalette {

    public static final byte TRANSPARENT = 0;
    public static final byte LIGHT_GREEN = 1;
    public static final byte LIGHT_BROWN = 2;
    public static final byte DARK_GREEN = 3;
    public static final byte DARK_BROWN = 4;

    private MapPalette() {}

    public static byte matchColor(Color color) { return 0; }
    public static Color getColor(byte index) { return Color.BLACK; }
}
