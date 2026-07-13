package org.bukkit;

public enum GameMode {
    SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR;
    public static GameMode getByValue(int value) { return values()[Math.min(value, values().length - 1)]; }
    public int getValue() { return ordinal(); }
}