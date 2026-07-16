package org.bukkit;

import java.util.HashMap;
import java.util.Map;

public enum ChatColor {
    BLACK('0', "black"),
    DARK_BLUE('1', "dark_blue"),
    DARK_GREEN('2', "dark_green"),
    DARK_AQUA('3', "dark_aqua"),
    DARK_RED('4', "dark_red"),
    DARK_PURPLE('5', "dark_purple"),
    GOLD('6', "gold"),
    GRAY('7', "gray"),
    DARK_GRAY('8', "dark_gray"),
    BLUE('9', "blue"),
    GREEN('a', "green"),
    AQUA('b', "aqua"),
    RED('c', "red"),
    LIGHT_PURPLE('d', "light_purple"),
    YELLOW('e', "yellow"),
    WHITE('f', "white"),
    MAGIC('k', "obfuscated"),
    BOLD('l', "bold"),
    STRIKETHROUGH('m', "strikethrough"),
    UNDERLINE('n', "underlined"),
    ITALIC('o', "italic"),
    RESET('r', "reset");

    private final char code;
    private final String name;
    private static final Map<Character, ChatColor> BY_CHAR = new HashMap<>();
    private static final Map<String, ChatColor> BY_NAME = new HashMap<>();
    public static final char COLOR_CHAR = '\u00A7';

    static {
        for (ChatColor c : values()) {
            BY_CHAR.put(c.code, c);
            BY_NAME.put(c.name().toUpperCase(), c);
        }
    }

    ChatColor(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public char getChar() { return code; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.valueOf(COLOR_CHAR) + code;
    }

    public static String stripColor(String input) {
        if (input == null) return null;
        return input.replaceAll(COLOR_CHAR + "[0-9a-fk-orA-FK-OR]", "");
    }

    public static String translateAlternateColorCodes(char altColorChar, String text) {
        if (text == null) return null;
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[i + 1]) >= 0) {
                chars[i] = COLOR_CHAR;
                i++;
            }
        }
        return new String(chars);
    }

    public static ChatColor valueOf(char code) {
        return BY_CHAR.get(Character.toLowerCase(code));
    }

    public static ChatColor getByChar(char code) {
        return BY_CHAR.get(Character.toLowerCase(code));
    }

    public static ChatColor getByChar(String code) {
        if (code == null || code.isEmpty()) return null;
        return BY_CHAR.get(Character.toLowerCase(code.charAt(0)));
    }

    public static boolean isColor(ChatColor color) {
        return color != null && !isFormat(color);
    }

    public boolean isColor() {
        return !isFormat(this);
    }

    public static boolean isFormat(ChatColor color) {
        return color == MAGIC || color == BOLD || color == STRIKETHROUGH
            || color == UNDERLINE || color == ITALIC;
    }

    public static String getLastColors(String input) {
        if (input == null || input.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        int len = input.length();
        for (int i = len - 2; i >= 0; i--) {
            if (input.charAt(i) == COLOR_CHAR) {
                char c = Character.toLowerCase(input.charAt(i + 1));
                ChatColor cc = BY_CHAR.get(c);
                if (cc != null) {
                    result.insert(0, cc.toString());
                    if (cc == RESET) break;
                }
            }
        }
        return result.toString();
    }
}
