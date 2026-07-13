package org.bukkit;

public enum Difficulty {
    PEACEFUL(0), EASY(1), NORMAL(2), HARD(3);
    private final int value;
    Difficulty(int value) { this.value = value; }
    public int getValue() { return value; }
    public static Difficulty getByValue(int value) {
        for (Difficulty d : values()) if (d.value == value) return d;
        return null;
    }
}
