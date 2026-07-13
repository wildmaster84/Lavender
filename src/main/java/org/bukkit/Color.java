package org.bukkit;

public class Color {
    public static final Color WHITE = new Color(0xFFFFFF);
    public static final Color SILVER = new Color(0xC0C0C0);
    public static final Color GRAY = new Color(0x808080);
    public static final Color DARK_GRAY = new Color(0x404040);
    public static final Color BLACK = new Color(0x000000);
    public static final Color RED = new Color(0xFF0000);
    public static final Color MAROON = new Color(0x800000);
    public static final Color YELLOW = new Color(0xFFFF00);
    public static final Color OLIVE = new Color(0x808000);
    public static final Color LIME = new Color(0x00FF00);
    public static final Color GREEN = new Color(0x008000);
    public static final Color AQUA = new Color(0x00FFFF);
    public static final Color TEAL = new Color(0x008080);
    public static final Color BLUE = new Color(0x0000FF);
    public static final Color NAVY = new Color(0x000080);
    public static final Color FUCHSIA = new Color(0xFF00FF);
    public static final Color PURPLE = new Color(0x800080);
    public static final Color ORANGE = new Color(0xFFA500);

    private final int rgb;

    public Color(int rgb) { this.rgb = rgb & 0xFFFFFF; }
    public Color(int r, int g, int b) { this.rgb = ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF); }

    public int getRGB() { return rgb; }
    public int getRed() { return (rgb >> 16) & 0xFF; }
    public int getGreen() { return (rgb >> 8) & 0xFF; }
    public int getBlue() { return rgb & 0xFF; }

    public static Color fromRGB(int r, int g, int b) { return new Color(r, g, b); }
    public static Color fromBGR(int b, int g, int r) { return new Color(r, g, b); }
    public static Color fromRGB(int rgb) { return new Color(rgb); }
    public static Color fromBGR(int bgr) { return new Color((bgr >> 16) & 0xFF, (bgr >> 8) & 0xFF, bgr & 0xFF); }
    public static Color mixColors(Color... colors) { return colors.length > 0 ? colors[0] : WHITE; }
}
