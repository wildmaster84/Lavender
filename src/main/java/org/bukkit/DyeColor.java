package org.bukkit;

public enum DyeColor {
    WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, LIGHT_GRAY, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK;
    public static DyeColor getByWoolData(byte data) { return values()[Math.max(0, Math.min(data, values().length - 1))]; }
    public static DyeColor getByDyeData(byte data) { return values()[Math.max(0, Math.min(data, values().length - 1))]; }
    public byte getWoolData() { return (byte) ordinal(); }
    public byte getDyeData() { return (byte) ((15 - ordinal()) & 0xF); }
    public org.bukkit.Color getColor() { return org.bukkit.Color.WHITE; }
    public org.bukkit.Color getFireworkColor() { return org.bukkit.Color.WHITE; }
}