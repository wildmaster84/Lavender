package net.md_5.bungee.api;

public enum ChatColor {
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    MAGIC('k'),
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINE('n'),
    ITALIC('o'),
    RESET('r');

    private final char code;
    private final String toString;

    ChatColor(char code) {
        this.code = code;
        this.toString = new String(new char[]{'\u00A7', code});
    }

    @Override
    public String toString() { return toString; }
    public char getChar() { return code; }

    public static ChatColor getByChar(char code) {
        for (ChatColor c : values()) if (c.code == code) return c;
        return null;
    }
    public static ChatColor of(String name) {
        if (name == null) return WHITE;
        if (name.startsWith("#")) return WHITE;
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return WHITE;
        }
    }
    public static ChatColor of(java.awt.Color color) { return WHITE; }
    public static String stripColor(String input) {
        if (input == null) return null;
        return input.replaceAll("\u00A7[0-9a-fk-orA-FK-OR]", "");
    }
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i+1]) > -1) {
                b[i] = '\u00A7';
                b[i+1] = Character.toLowerCase(b[i+1]);
            }
        }
        return new String(b);
    }
}
